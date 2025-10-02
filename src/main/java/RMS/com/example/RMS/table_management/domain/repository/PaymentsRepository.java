package RMS.com.example.RMS.table_management.domain.repository;

import RMS.com.example.RMS.table_management.domain.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<PaymentEntity, Long> {
    boolean existsByName(String name);
}
