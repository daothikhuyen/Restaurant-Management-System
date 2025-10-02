package RMS.com.example.RMS.menu.domain.repository;

import RMS.com.example.RMS.menu.domain.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByName(String name);

    List<ProductEntity> findByCategoryId(Long categoryId);
}
