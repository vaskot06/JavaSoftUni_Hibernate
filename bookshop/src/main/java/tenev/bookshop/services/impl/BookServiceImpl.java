package tenev.bookshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenev.bookshop.entities.Author;
import tenev.bookshop.entities.Book;
import tenev.bookshop.entities.Category;
import tenev.bookshop.entities.enums.AgeRestriction;
import tenev.bookshop.entities.enums.EditionType;
import tenev.bookshop.repositories.AuthorRepository;
import tenev.bookshop.repositories.BookRepository;
import tenev.bookshop.repositories.CategoryRepository;
import tenev.bookshop.services.BookService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedBooks() throws IOException, ParseException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\Hibernate\\5.SPRING DATA INTRO\\bookshop\\src\\main\\resources\\files\\books.txt"));

        String line = bufferedReader.readLine();


        CategoryServiceImpl categoryImpl = new CategoryServiceImpl(this.categoryRepository);
        categoryImpl.seedCategory();

        AuthorServiceImpl authorService = new AuthorServiceImpl(this.authorRepository);
        authorService.seedAuthors();

        List<Author> authors = this.authorRepository.findAll();
        while (line != null) {

            String[] dataArray = line.split("\\s+");

            Random randomAuthor = new Random();
            long authorIndex = randomAuthor.nextInt(this.authorRepository.findAll().size());


            Author author = authors.get((int) authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(dataArray[0])];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/m/yyyy");
            Date releaseDate = simpleDateFormat.parse(dataArray[1]);
            int copies = Integer.parseInt(dataArray[2]);
            BigDecimal price = new BigDecimal(dataArray[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(dataArray[4])];

            String title = "";
            StringBuilder sb = new StringBuilder();
            for (int i = 5; i < dataArray.length; i++) {
                sb.append(dataArray[i]).append(" ");
            }
            title = sb.toString().trim();

            Book book = new Book();
            book.setEditionType(editionType);
            book.setDate(releaseDate);
            book.setCopy(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setAuthor(author);
            book.setDescription("Random description which every book has. :)");

            Random random = new Random();
            int randomBound = random.nextInt(4);

            Set<Category> categorySet = new HashSet<>();
            for (int i = 0; i < randomBound; i++) {
                Random randomCategory = new Random();
                long categoryIndex = randomCategory.nextInt((int) this.categoryRepository.count());

                Category category = this.categoryRepository.getById(categoryIndex);

                categorySet.add(category);
            }
            book.setCategorySet(categorySet);

            this.bookRepository.saveAndFlush(book);


            line = bufferedReader.readLine();
        }


    }
}
