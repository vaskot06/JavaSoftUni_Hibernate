package tenev.bookshop.services.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tenev.bookshop.entities.Author;
import tenev.bookshop.entities.Book;
import tenev.bookshop.repositories.AuthorRepository;
import tenev.bookshop.repositories.BookRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class QueriesToExecute {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public QueriesToExecute(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public void getAllByNameAfterDate() {
        List<Book> books = null;
        try {
            books = this.bookRepository.getAllByDateAfter(new SimpleDateFormat("yyyy").parse("2000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert books != null;
        books.forEach(e -> System.out.println(e.getTitle()));
    }

    public void getAuthorsByBooksBefore() throws ParseException {

        List<Book> books = this.bookRepository.getAllByDateBefore(new SimpleDateFormat("yyyy").parse("1990"));

        books.forEach(a -> System.out.println(a.getAuthor().getFirstName() + " " + a.getAuthor().getLastName()));
    }

    public void getByBookCount() {
        List<Author> authors = this.authorRepository.getAllByOrderByBooks()
                .stream().sorted(Comparator.comparing(a->a.getBooks().size())).collect(Collectors.toList());

        for (Author author : authors) {
            System.out.println(author.getFirstName() + " " + author.getLastName() + " " + author.getBooks().size());
        }
    }


    public void getByAuthor(){

        Author author = this.authorRepository.getOne(4L);
        List<Book> books = this.bookRepository.getAllByAuthorEqualsOrderByDateDescTitleAsc(author);

        for (Book book : books) {
            System.out.println(book.getTitle() + " " + book.getDate() + " " + book.getCopy());
        }

        System.out.println();

    }
}
