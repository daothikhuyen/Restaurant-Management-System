package RMS.com.example.RMS.billing.web;

import RMS.com.example.RMS.billing.domain.model.BillingStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillingResponse {
    private Long orderId;
    private Long tableId;
    private Long customerId;
    private Double totalAmount;
    private Double tax ;
    private Double discount ;
    private String notes;
    private BillingStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
