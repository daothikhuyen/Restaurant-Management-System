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
@Table(name="user")
public class AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private int activated;
    private String activationKey;
    private String resetKey;
    private String targetKey;
    private String imageUrl;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerEntity profile;
}
