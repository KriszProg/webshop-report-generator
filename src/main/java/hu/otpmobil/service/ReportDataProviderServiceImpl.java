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
        Map<UniqueId, WebShopCustomer> webShopCustomerMap = dataStore.getWebShopCustomers().stream()
                .collect(Collectors.toMap(WebShopCustomer::getUniqueId, customer -> customer));

        return dataStore.getPayments().stream()
                .collect(Collectors.groupingBy(
                        Payment::getUniqueId,
                        Collectors.summingInt(Payment::getAmount)
                ))
                .entrySet().stream()
                .map(entry -> {
                    WebShopCustomer webShopCustomer = webShopCustomerMap.get(entry.getKey());
                    return new PurchaseByWebShopCustomer(
                            webShopCustomer,
                            entry.getValue()
                    );
                })
                .sorted(Comparator
                        .comparing((PurchaseByWebShopCustomer item) -> item.getWebShopCustomer().getCustomer().getName())
                        .thenComparing(item -> item.getWebShopCustomer().getUniqueId().getWebShopId())
                )
                .collect(Collectors.toList());
    }

    private void initPurchaseByCustomerListIfEmpty() {
        if (purchaseByCustomerList.isEmpty()) {
            purchaseByCustomerList = dataStore.getCustomerAndPaymentList().stream()
                    .collect(Collectors.groupingBy(
                            CustomerAndPayment::getCustomer,
                            Collectors.summingInt(CustomerAndPayment::getPaymentAmount)
                    ))
                    .entrySet().stream()
                    .map(entry -> new PurchaseByCustomer(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
        }
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
