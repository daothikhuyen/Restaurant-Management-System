package RMS.com.example.RMS.billing.domain;

import RMS.com.example.RMS.billing.domain.model.BillingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillingRepository extends JpaRepository<BillingEntity, Long> {
    boolean existsByOrderId(Long orderId);

    Optional<BillingEntity> findByOrderId(Long orderId);
}
