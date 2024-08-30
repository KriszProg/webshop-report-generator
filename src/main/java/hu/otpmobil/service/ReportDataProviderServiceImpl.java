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
    private List<CustomerAndPayment> customerAndPaymentList = new ArrayList<>();
    private List<PurchaseByCustomerDetails> purchaseByCustomerDetailsList = new ArrayList<>();

    public ReportDataProviderServiceImpl() {
        this.dataStore = DataStore.getInstance();
    }

    @Override
    public List<PurchaseByCustomerDetails> getDataForPurchaseByCustomerDetailsReport() {
        initPurchaseByCustomerDetailsListIfEmpty();
        return getPurchaseByCustomerDetailsListSortedByTotalAmount(SortDirection.DESC);
    }

    @Override
    public List<PurchaseByCustomerDetails> getDataForTopCustomerReport() {
        initPurchaseByCustomerDetailsListIfEmpty();
        List<PurchaseByCustomerDetails> sortedList = getPurchaseByCustomerDetailsListSortedByTotalAmount(SortDirection.DESC);

        List<Integer> topTotalPaymentList = sortedList.stream()
                .map(PurchaseByCustomerDetails::getTotalPurchaseAmount)
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
                                payment -> payment.getDetails().getPaymentType(),
                                Collectors.summingInt(payment -> payment.getDetails().getAmount())
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

    private void initPurchaseByCustomerDetailsListIfEmpty() {
        if (purchaseByCustomerDetailsList.isEmpty()) {
            initPurchaseByCustomerDetailsList();
        }
    }

    private void initPurchaseByCustomerDetailsList() {
        initCustomerAndPaymentListIfEmpty();
        purchaseByCustomerDetailsList = customerAndPaymentList.stream()
                .collect(Collectors.groupingBy(
                        CustomerAndPayment::getCustomer,
                        Collectors.summingInt(cap -> cap.getPayment().getAmount())
                ))
                .entrySet().stream()
                .map(entry -> new PurchaseByCustomerDetails(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private void initCustomerAndPaymentListIfEmpty() {
        if (customerAndPaymentList.isEmpty()) {
            customerAndPaymentList = dataStore.getCustomerAndPaymentList();
        }
    }

    private List<PurchaseByCustomerDetails> getPurchaseByCustomerDetailsListSortedByTotalAmount(SortDirection direction) {
        if (direction == SortDirection.DESC) {
            return getPurchaseByCustomerDetailsListSortedByTotalAmountHighestFirst();
        } else {
            return getPurchaseByCustomerDetailsListSortedByTotalAmountLowestFirst();
        }
    }

    private List<PurchaseByCustomerDetails> getPurchaseByCustomerDetailsListSortedByTotalAmountHighestFirst() {
        return purchaseByCustomerDetailsList.stream()
                .sorted(Comparator.comparing(PurchaseByCustomerDetails::getTotalPurchaseAmount).reversed())
                .collect(Collectors.toList());
    }

    private List<PurchaseByCustomerDetails> getPurchaseByCustomerDetailsListSortedByTotalAmountLowestFirst() {
        return purchaseByCustomerDetailsList.stream()
                .sorted(Comparator.comparing(PurchaseByCustomerDetails::getTotalPurchaseAmount))
                .collect(Collectors.toList());
    }

}
