package RMS.com.example.RMS.billing.web;

import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillingRequest {
    @NotNull(message = "Order ID is required")
    private Long orderId;

    @NotNull(message = "Table ID is required")
    private Long tableId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Total amount is required")
    @Min(value = 0, message = "Total amount must be greater than or equal to 0")
    private Double totalAmount;

    @Size(max = 255, message = "Notes cannot exceed 255 characters")
    private String notes;
}
