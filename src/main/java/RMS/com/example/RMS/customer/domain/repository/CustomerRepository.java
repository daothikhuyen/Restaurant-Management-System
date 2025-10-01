package RMS.com.example.RMS.customer.domain.repository;
import RMS.com.example.RMS.customer.domain.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByEmail(String email);

    Optional<AuthRepository> findByEmail(String email);
}
