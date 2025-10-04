package RMS.com.example.RMS.payment.web;

import RMS.com.example.RMS.payment.domain.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {

    private String name;
    private PaymentStatus status;
    private String createdBy;
    private String updatedBy;
}
