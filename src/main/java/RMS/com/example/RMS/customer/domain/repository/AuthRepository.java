package RMS.com.example.RMS.customer.domain.repository;

import RMS.com.example.RMS.customer.domain.model.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

    boolean existsByUsername(String username);

    Optional<AuthEntity> findByUsername(String username);
}
