package RMS.com.example.RMS.customer.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="profile")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private LocalDateTime dob;
    private String address;
    private char gender;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean isDeleted = false;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false, unique = true) // unique đảm bảo 1-1
    private AuthEntity user;
}
