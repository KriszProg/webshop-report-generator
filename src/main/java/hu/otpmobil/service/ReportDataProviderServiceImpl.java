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
    private List<CustomerPayment> customerPaymentList = new ArrayList<>();

    public ReportDataProviderServiceImpl() {
        this.dataStore = DataStore.getInstance();
    }

    @Override
    public List<CustomerPayment> getDataForCustomerPaymentReport() {
        initCustomerPaymentListIfEmpty();
        return customerPaymentList;
    }

    @Override
    public List<CustomerPayment> getDataForTopCustomerReport() {
        initCustomerPaymentListIfEmpty();
        List<CustomerPayment> sortedCustomerPayment = getCustomerPaymentSortedByTotalPayment(SortDirection.DESC);

        List<Integer> topTotalPaymentList = sortedCustomerPayment.stream()
                .map(CustomerPayment::getTotalPayment)
                .distinct()
                .limit(NUMBER_OF_TOP_LIST_ELEMENTS)
                .collect(Collectors.toList());

        return sortedCustomerPayment.stream()
                .filter(customerPayment -> topTotalPaymentList.contains(customerPayment.getTotalPayment()))
                .collect(Collectors.toList());
    }

    @Override
    public List<WebShopSales> getDataForWebShopSalesReport() {
        return dataStore.getPayments().stream()
                .collect(Collectors.groupingBy(
                        payment -> payment.getUniqueId().getWebShopId(),
                        Collectors.groupingBy(
                                Payment::getPaymentType,
                                Collectors.summingInt(Payment::getAmount)
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

    private void initCustomerPaymentListIfEmpty() {
        if (customerPaymentList.isEmpty()) {
            initCustomerPaymentList();
        }
    }

    private void initCustomerPaymentList() {
        Map<UniqueId, Customer> customerMap = dataStore.getCustomers().stream()
                .collect(Collectors.toMap(Customer::getUniqueId, customer -> customer));

        customerPaymentList = dataStore.getPayments().stream()
                .collect(Collectors.groupingBy(
                        Payment::getUniqueId,
                        Collectors.summingInt(Payment::getAmount)
                ))
                .entrySet().stream()
                .map(entry -> {
                    Customer customer = customerMap.get(entry.getKey());
                    return new CustomerPayment(
                            customer.getName(),
                            customer.getAddress(),
                            entry.getValue()
                    );
                })
                .collect(Collectors.toList());
    }

    private List<CustomerPayment> getCustomerPaymentSortedByTotalPayment(SortDirection direction) {
        if (direction == SortDirection.DESC) {
            return getCustomerPaymentSortedByTotalPaymentHighestFirst();
        } else {
            return getCustomerPaymentSortedByTotalPaymentLowestFirst();
        }
    }

    private List<CustomerPayment> getCustomerPaymentSortedByTotalPaymentHighestFirst() {
        return customerPaymentList.stream()
                .sorted(Comparator.comparing(CustomerPayment::getTotalPayment).reversed())
                .collect(Collectors.toList());
    }

    private List<CustomerPayment> getCustomerPaymentSortedByTotalPaymentLowestFirst() {
        return customerPaymentList.stream()
                .sorted(Comparator.comparing(CustomerPayment::getTotalPayment))
                .collect(Collectors.toList());
    }

}
