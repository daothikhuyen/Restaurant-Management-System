package RMS.com.example.RMS.order.domain.repository;

import RMS.com.example.RMS.order.domain.model.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    List<OrderDetailEntity> findAllByOrderId(Long orderId);
}
