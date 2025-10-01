package RMS.com.example.RMS.table_management.web.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableRequest {
    private int capacity;
    private String name;
    private String area;
    private String createdBy;
    private String updatedBy;
}
