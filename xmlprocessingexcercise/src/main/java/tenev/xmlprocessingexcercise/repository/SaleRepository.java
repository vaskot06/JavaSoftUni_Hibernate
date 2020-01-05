package tenev.xmlprocessingexcercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.xmlprocessingexcercise.domain.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
