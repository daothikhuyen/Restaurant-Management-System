package RMS.com.example.RMS.order.web.response;

import RMS.com.example.RMS.billing.web.BillingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long userId;
    private Long tableId;
    private Long paymentId;
    private String orderType;
    private double totalAmount;
    private String createdBy;
    private String updatedBy;
    private String notes;

}
