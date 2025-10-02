package RMS.com.example.RMS.table_management.domain.repository;

import RMS.com.example.RMS.table_management.domain.model.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
    boolean existsByName(String name);
}
