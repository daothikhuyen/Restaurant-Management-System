package RMS.com.example.RMS.table_management.web.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    private Long userId;
    private Long tableId;
    private Long paymentId;
    private String orderType;
    private double totalAmount;
    private String createdBy;
    private String updatedBy;
}
