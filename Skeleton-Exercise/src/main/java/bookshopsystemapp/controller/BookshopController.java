package bookshopsystemapp.controller;

import bookshopsystemapp.domain.entities.Author;
import bookshopsystemapp.domain.entities.Book;
import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Controller
public class BookshopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;


    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;

    }

    @Override
    public void run(String... strings) throws Exception {
        this.authorService.seedAuthors();
        this.categoryService.seedCategories();
        this.bookService.seedBooks();

        //firstTask();
        //secondTask();
        // thirdTask();
        // fourthTask();
        //fifthTask();
        //sixthTask();
        //seventhTask();
        //eightTask();
        //ninthTask();
        tenthTask();


    }

    private void tenthTask() {
        List<Object[]> authors = this.authorService.totalCopiesPerAuthor();
        authors.forEach(a -> System.out.println(a[0] + " " + a[1]));
    }

    private void ninthTask() throws IOException {
        Integer number = this.bookService.countAllByTitle();
        System.out.println(number);
    }

    private void eightTask() throws IOException {

        List<Book> books = this.bookService.bookTitleSearch();
        books.forEach(b -> System.out.println(b.getTitle() + " (" + b.getAuthor().getFirstName()
                + " " + b.getAuthor().getLastName() + ")"));
    }

    private void seventhTask() throws IOException {
        List<Book> books = this.bookService.bookSearch();
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void firstTask() throws IOException {
        List<Book> books = this.bookService.bookTitlesByAgeRestriction();
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void secondTask() throws IOException {
        List<Book> books = this.bookService.goldenBooks();
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void thirdTask() throws IOException {
        List<Book> books = this.bookService.booksByPrice();
        books.forEach(b -> System.out.println(b.getTitle() + " " + b.getPrice()));
    }

    private void fourthTask() throws IOException {
        List<Book> books = this.bookService.notReleasedBooks();
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void fifthTask() throws IOException {
        List<Book> books = this.bookService.booksReleasedBeforeDate();
        books.forEach(b -> System.out.println(b.getTitle() + " " + b.getEditionType() + " " + b.getPrice()));
    }

    private void sixthTask() {
        List<Author> authors = this.authorService.authorsSearch();

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

}
