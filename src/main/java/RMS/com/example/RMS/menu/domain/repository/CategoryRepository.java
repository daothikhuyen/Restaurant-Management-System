package RMS.com.example.RMS.menu.domain.repository;

import RMS.com.example.RMS.menu.domain.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByName(String name);
}
