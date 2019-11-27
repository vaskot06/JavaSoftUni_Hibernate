package tenev.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tenev.bookshop.repositories.AuthorRepository;
import tenev.bookshop.repositories.BookRepository;
import tenev.bookshop.services.impl.BookServiceImpl;
import tenev.bookshop.services.impl.SeedDatabase;
import tenev.bookshop.services.queries.QueriesToExecute;

import java.text.ParseException;

@SpringBootApplication
@Component
@Transactional
public class ConsoleRunner implements CommandLineRunner {

    private BookServiceImpl bookService;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(BookServiceImpl bookService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws ParseException {

        SeedDatabase seedDatabase = new SeedDatabase(this.bookService);
        seedDatabase.seedDB();

        QueriesToExecute queriesToExecute = new QueriesToExecute(this.bookRepository, this.authorRepository);

        //        System.out.println("The below results are for the first task: ");
        //        queriesToExecute.getAllByNameAfterDate();

        //        System.out.println("The below results are for the second task: ");
        //        queriesToExecute.getAuthorsByBooksBefore();

        //        System.out.println("The below results are for the third task: ");
        //        queriesToExecute.getByBookCount();


        queriesToExecute.getByAuthor();

    }
}
