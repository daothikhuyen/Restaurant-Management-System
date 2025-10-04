package RMS.com.example.RMS.billing.domain.service;

import RMS.com.example.RMS.billing.web.BillingResponse;

public interface IBillingService {
    BillingResponse createBillingFromOrder(Long orderId, Long tableId, Long userId, double totalAmount, String notes);

    BillingResponse updateBilling(Long id, Long tableId, Long userId, double totalAmount, String notes);
}
