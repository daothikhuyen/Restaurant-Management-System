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
public class OrderTransactionResponse {
    private OrderResponse orderResponse;
    private List<OrderDetailResponse> orderDetails;
    private BillingResponse billing;
}
