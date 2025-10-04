package RMS.com.example.RMS.table_management.web;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableRequest {
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 100, message = "Capacity must not exceed 100")
    private int capacity;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Area is required")
    @Size(max = 50, message = "Area must not exceed 50 characters")
    private String area;

    @NotBlank(message = "CreatedBy is required")
    private String createdBy;
    private String updatedBy;
}
