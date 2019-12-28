package tenev.jsonexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.jsonexercise.domain.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
