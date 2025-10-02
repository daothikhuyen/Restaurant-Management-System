package RMS.com.example.RMS.menu.web.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    private Long categoryId;
    private String name;
    private String description;
    private double price;
    private double pricePromotion;
    private String imageUrl;
    private String createdBy;
    private String updatedBy;
}
