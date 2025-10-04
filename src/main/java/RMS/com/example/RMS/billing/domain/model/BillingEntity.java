package RMS.com.example.RMS.billing.domain.model;

import RMS.com.example.RMS.billing.domain.model.BillingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="billing")
public class BillingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long tableId;
    private Long customerId;
    private Double totalAmount;
    private Double tax = 5.0;
    private Double discount = 0.0;
    @Column(nullable = true)
    private String notes;
    @Enumerated(EnumType.STRING)
    private BillingStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
