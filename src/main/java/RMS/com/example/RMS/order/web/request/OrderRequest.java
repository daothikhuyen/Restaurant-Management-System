package RMS.com.example.RMS.order.web.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Table ID is required")
    private Long tableId;

    @NotNull(message = "Payment ID is required")
    private Long paymentId;

    @NotNull(message = "Order type is required")
    @Size(max = 50, message = "Order type max length is 50")
    private String orderType;

    @Min(value = 0, message = "Total amount must be greater than or equal to 0")
    private double totalAmount;

    @NotNull(message = "Created by is required")
    @Size(max = 50)
    private String createdBy;

    @Size(max = 50)
    private String updatedBy;

    @Size(max = 255, message = "Notes cannot exceed 255 characters")
    private String notes;

    @NotEmpty(message = "Order must have at least one detail")
    private List<@Valid OrderDetailRequest> orderDetails;
}
