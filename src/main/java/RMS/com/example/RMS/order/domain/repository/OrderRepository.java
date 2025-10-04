package RMS.com.example.RMS.order.domain.repository;

import RMS.com.example.RMS.order.domain.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    boolean existsByTableId(Long tableId);
}
