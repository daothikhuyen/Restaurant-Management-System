package RMS.com.example.RMS.customer.domain;
import RMS.com.example.RMS.customer.web.CustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByEmail(String email);

    Optional<CustomerEntity> findByEmail(String email);
}
