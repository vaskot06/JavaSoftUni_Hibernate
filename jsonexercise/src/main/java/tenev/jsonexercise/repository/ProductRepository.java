package tenev.jsonexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.jsonexercise.domain.entity.Product;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Set<Product> getAllByPriceGreaterThanAndPriceLessThanAndBuyerIsNullOrderByPriceAsc(BigDecimal lessThan, BigDecimal greaterThan);

}
