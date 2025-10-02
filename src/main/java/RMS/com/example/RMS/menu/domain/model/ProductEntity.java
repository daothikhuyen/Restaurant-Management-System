package RMS.com.example.RMS.menu.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private double price;
    private double pricePromotion;
    private String imageUrl;
    private boolean isDeleted = false;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
