package tenev.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.bookshop.entities.Author;
import tenev.bookshop.entities.Book;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> getAllByOrderByBooks();

}
