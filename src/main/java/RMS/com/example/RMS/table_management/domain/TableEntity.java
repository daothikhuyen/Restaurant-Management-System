package RMS.com.example.RMS.table_management.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="restaurant_table")
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int capacity;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TableStatus status;
    private String name;
    private String area;
    private boolean isDeleted = false;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
