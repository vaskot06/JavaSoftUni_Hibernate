package tenev.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.bookshop.entities.Author;
import tenev.bookshop.entities.Book;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getAllByDateAfter(Date date);

    List<Book> getAllByDateBefore(Date date);


    List<Book> getAllByAuthorEqualsOrderByDateDescTitleAsc(Author author);


}
