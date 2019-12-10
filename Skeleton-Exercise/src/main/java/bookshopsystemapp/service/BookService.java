package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.Book;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    List<Book> bookTitlesByAgeRestriction() throws IOException;

    List<Book> goldenBooks() throws IOException;

    List<Book> booksByPrice();

    List<Book> notReleasedBooks() throws IOException;

    List<Book> booksReleasedBeforeDate() throws IOException;

    List<Book> bookSearch() throws IOException;

    List<Book> bookTitleSearch() throws IOException;

    Integer countAllByTitle() throws IOException;

}
