package tenev.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.bookshop.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getById (long id);
}
