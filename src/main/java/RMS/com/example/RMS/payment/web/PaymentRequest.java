package RMS.com.example.RMS.payment.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {

    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Name can have at most 100 characters")
    private String name;

    @NotBlank(message = "CreatedBy must not be blank")
    @Size(max = 50, message = "CreatedBy can have at most 50 characters")
    private String createdBy;

    @Size(max = 50, message = "UpdatedBy can have at most 50 characters")
    private String updatedBy;
}
