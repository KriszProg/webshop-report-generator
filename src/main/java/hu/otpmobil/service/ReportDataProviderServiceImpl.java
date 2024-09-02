package hu.otpmobil.service;

import hu.otpmobil.data.DataStore;
import hu.otpmobil.model.*;
import hu.otpmobil.util.SortDirection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static hu.otpmobil.config.ApplicationConstants.NUMBER_OF_TOP_LIST_ELEMENTS;

public class ReportDataProviderServiceImpl implements ReportDataProviderService {

    private final DataStore dataStore;
    private List<WebShopCustomerAndPayment> webShopCustomerAndPaymentList = new ArrayList<>();
    private List<PurchaseByCustomer> purchaseByCustomerList = new ArrayList<>();

    public ReportDataProviderServiceImpl() {
        this.dataStore = DataStore.getInstance();
    }

    @Override
    public List<PurchaseByCustomer> getDataForPurchaseByCustomerReport() {
        initPurchaseByCustomerListIfEmpty();
        return getPurchaseByCustomerListSortedByTotalAmount(SortDirection.DESC);
    }

    @Override
    public List<PurchaseByCustomerPerYear> getDataForPurchaseByCustomerPerYearReport() {
        initWebShopCustomerAndPaymentListIfEmpty();
        return webShopCustomerAndPaymentList.stream()
                .collect(Collectors.groupingBy(
                                webShopCustomerAndPayment -> webShopCustomerAndPayment.getPaymentDate().getYear(),
                                Collectors.groupingBy(
                                        webShopCustomerAndPayment -> webShopCustomerAndPayment.getWebShopCustomer().getCustomer(),
                                        Collectors.summingInt(WebShopCustomerAndPayment::getPaymentAmount))
                        )
                )
                .entrySet().stream()
                .flatMap(yearEntry -> yearEntry.getValue().entrySet().stream()
                        .map(customerEntry ->
                                new PurchaseByCustomerPerYear(yearEntry.getKey(), customerEntry.getKey(), customerEntry.getValue())
                        )
                        .sorted(Comparator
                                .comparing(PurchaseByCustomerPerYear::getYear)
                                .thenComparing(PurchaseByCustomerPerYear::getTotalPurchaseAmount).reversed()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseByCustomer> getDataForTopCustomerReport() {
        initPurchaseByCustomerListIfEmpty();
        List<PurchaseByCustomer> sortedList = getPurchaseByCustomerListSortedByTotalAmount(SortDirection.DESC);

        List<Integer> topTotalPaymentList = sortedList.stream()
                .map(PurchaseByCustomer::getTotalPurchaseAmount)
                .distinct()
                .limit(NUMBER_OF_TOP_LIST_ELEMENTS)
                .collect(Collectors.toList());

        return sortedList.stream()
                .filter(purchaseByCustomerDetails ->
                        topTotalPaymentList.contains(purchaseByCustomerDetails.getTotalPurchaseAmount()))
                .collect(Collectors.toList());
    }

    @Override
    public List<WebShopSales> getDataForWebShopSalesReport() {
        return dataStore.getPayments().stream()
                .collect(Collectors.groupingBy(
                        payment -> payment.getUniqueId().getWebShopId(),
                        Collectors.groupingBy(
                                Payment::getPaymentType,
                                Collectors.summingInt(payment -> payment.getAmount())
                        )
                ))
                .entrySet().stream()
                .map(entry -> {
                    String webShopId = entry.getKey();
                    Map<String, Integer> paymentTypeMap = entry.getValue();
                    Integer cardPaymentTotal = paymentTypeMap.getOrDefault(PaymentType.CARD.getName(), 0);
                    Integer transferPaymentTotal = paymentTypeMap.getOrDefault(PaymentType.TRANSFER.getName(), 0);
                    return new WebShopSales(webShopId, cardPaymentTotal, transferPaymentTotal);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseByWebShopCustomer> getDataForPurchaseByWebShopCustomerReport() {
        initWebShopCustomerAndPaymentListIfEmpty();
        return webShopCustomerAndPaymentList.stream()
                .collect(Collectors.groupingBy(
                        WebShopCustomerAndPayment::getWebShopCustomer,
                        Collectors.summingInt(WebShopCustomerAndPayment::getPaymentAmount))
                )
                .entrySet().stream()
                .map(entry -> new PurchaseByWebShopCustomer(entry.getKey(), entry.getValue()))
                .sorted(Comparator
                        .comparing((PurchaseByWebShopCustomer item) -> item.getWebShopCustomer().getCustomer().getName())
                        .thenComparing(item -> item.getWebShopCustomer().getUniqueId().getWebShopId())
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseByWebShopCustomerPerYear> getDataForPurchaseByWebShopCustomerPerYearReport() {
        initWebShopCustomerAndPaymentListIfEmpty();
        return webShopCustomerAndPaymentList.stream()
                .collect(Collectors.groupingBy(
                        webShopCustomerAndPayment -> webShopCustomerAndPayment.getPaymentDate().getYear(),
                        Collectors.groupingBy(
                                WebShopCustomerAndPayment::getWebShopCustomer,
                                Collectors.summingInt(WebShopCustomerAndPayment::getPaymentAmount))
                        )
                )
                .entrySet().stream()
                .flatMap(yearEntry -> yearEntry.getValue().entrySet().stream()
                        .map(webShopCustomerEntry ->
                                new PurchaseByWebShopCustomerPerYear(yearEntry.getKey(),
                                        webShopCustomerEntry.getKey(),
                                        webShopCustomerEntry.getValue())
                        )
                        .sorted(Comparator
                                .comparing(PurchaseByWebShopCustomerPerYear::getYear)
                                .thenComparing(purchaseByWebShopCustomerPerYear ->
                                        purchaseByWebShopCustomerPerYear.getWebShopCustomer().getCustomer().getName())
                                .thenComparing(purchaseByWebShopCustomerPerYear ->
                                        purchaseByWebShopCustomerPerYear.getWebShopCustomer().getUniqueId().getWebShopId())
                        )
                )
                .collect(Collectors.toList());
    }

    private void initPurchaseByCustomerListIfEmpty() {
        if (purchaseByCustomerList.isEmpty()) {
            initWebShopCustomerAndPaymentListIfEmpty();
            purchaseByCustomerList = webShopCustomerAndPaymentList.stream()
                    .collect(Collectors.groupingBy(
                            webShopCustomerAndPayment -> webShopCustomerAndPayment.getWebShopCustomer().getCustomer(),
                            Collectors.summingInt(WebShopCustomerAndPayment::getPaymentAmount)
                    ))
                    .entrySet().stream()
                    .map(entry -> new PurchaseByCustomer(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
        }
    }

    private void initWebShopCustomerAndPaymentListIfEmpty() {
        webShopCustomerAndPaymentList = dataStore.getWebShopCustomerAndPaymentList();
    }

    private List<PurchaseByCustomer> getPurchaseByCustomerListSortedByTotalAmount(SortDirection direction) {
        if (direction == SortDirection.DESC) {
            return getPurchaseByCustomerListSortedByTotalAmountHighestFirst();
        } else {
            return getPurchaseByCustomerListSortedByTotalAmountLowestFirst();
        }
    }

    private List<PurchaseByCustomer> getPurchaseByCustomerListSortedByTotalAmountHighestFirst() {
        return purchaseByCustomerList.stream()
                .sorted(Comparator.comparing(PurchaseByCustomer::getTotalPurchaseAmount).reversed())
                .collect(Collectors.toList());
    }

    private List<PurchaseByCustomer> getPurchaseByCustomerListSortedByTotalAmountLowestFirst() {
        return purchaseByCustomerList.stream()
                .sorted(Comparator.comparing(PurchaseByCustomer::getTotalPurchaseAmount))
                .collect(Collectors.toList());
    }

}
