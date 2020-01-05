package tenev.xmlprocessingexcercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.xmlprocessingexcercise.domain.entity.Supplier;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findAllByImporterIsFalse();
}
