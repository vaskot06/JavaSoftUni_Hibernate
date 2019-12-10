package bookshopsystemapp.repository;

import bookshopsystemapp.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    List<Author> findAllByFirstNameEndsWith(String end);

    List<Author> findAllByLastNameStartingWith(String start);

    @Query(value = "SELECT CONCAT(a.firstName, ' ', a.lastName) AS name, SUM(b.copies) AS sumc FROM Author AS a JOIN Book AS b " +
            " ON a.id = b.author.id GROUP BY CONCAT(a.firstName, ' ', a.lastName) ORDER BY SUM(b.copies) DESC ")
    List<Object[]> totalCopiesPerAuthor();
}
