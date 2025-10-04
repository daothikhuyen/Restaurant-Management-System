package RMS.com.example.RMS.payment.domain;

import RMS.com.example.RMS.payment.domain.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<PaymentEntity, Long> {
    boolean existsByName(String name);
}
