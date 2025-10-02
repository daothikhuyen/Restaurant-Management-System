package RMS.com.example.RMS.table_management.web.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {

    private String name;
    private String createdBy;
    private String updatedBy;
}
