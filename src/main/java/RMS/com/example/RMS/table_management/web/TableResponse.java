package RMS.com.example.RMS.table_management.web;

import RMS.com.example.RMS.table_management.domain.model.TableStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableResponse {

    private int capacity;
    private TableStatus status;
    private String name;
    private String area;
    private String createdBy;
    private String updatedBy;
}
