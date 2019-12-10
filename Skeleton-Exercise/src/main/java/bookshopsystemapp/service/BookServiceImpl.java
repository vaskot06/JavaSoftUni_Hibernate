package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.*;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final static String BOOKS_FILE_PATH = "C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\" +
            "Hibernate\\6.SPRING_DATA_ADVANCED_QUERING\\" +
            "Skeleton-Exercise\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    private BufferedReader bufferedReader;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String line : booksFileContent) {
            String[] lineParams = line.split("\\s+");

            Book book = new Book();
            book.setAuthor(this.getRandomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(lineParams[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(lineParams[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < lineParams.length; i++) {
                title.append(lineParams[i]).append(" ");
            }

            book.setTitle(title.toString().trim());

            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public List<String> getAllBooksTitlesAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

        return books.stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllAuthorsWithBookBefore() {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return books.stream().map(b -> String.format("%s %s", b.getAuthor().getFirstName(), b.getAuthor().getLastName())).collect(Collectors.toSet());
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

        return this.authorRepository.findById(randomId).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();
        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.findById(randomId).orElse(null);
    }

    public List<Book> bookTitlesByAgeRestriction() throws IOException {
        String ageRestrictionInput = this.bufferedReader.readLine();

        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionInput.toUpperCase());

        List<Book> books = this.bookRepository.findAllByAgeRestrictionEquals(ageRestriction);

        return books;
    }

    public List<Book> goldenBooks() throws IOException {

        String editionTypeInput = "GOLD".toUpperCase();

        EditionType editionType = EditionType.valueOf(editionTypeInput);
        Integer copies = 5000;

        List<Book> books = this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copies);

        return books;
    }

    @Override
    public List<Book> booksByPrice() {
        BigDecimal lower = BigDecimal.valueOf(5);
        BigDecimal higher = BigDecimal.valueOf(40);

        List<Book> books = this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lower, higher);

        return books;
    }

    @Override
    public List<Book> notReleasedBooks() throws IOException {

        String dateInput = this.bufferedReader.readLine();
        //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH);

        DateTimeFormatter formatterBefore = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();

        DateTimeFormatter formatterAfter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 12)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 31)
                .toFormatter();

        LocalDate dateBefore = LocalDate.parse(dateInput, formatterBefore);
        LocalDate dateAfter = LocalDate.parse(dateInput, formatterAfter);

        List<Book> books = this.bookRepository.findAllByReleaseDateIsBeforeOrReleaseDateIsAfter(dateBefore, dateAfter);

        return books;
    }

    @Override
    public List<Book> booksReleasedBeforeDate() throws IOException {

        String input = this.bufferedReader.readLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(input, formatter);

        return this.bookRepository.findAllByReleaseDateIsBefore(date);


    }

    @Override
    public List<Book> bookSearch() throws IOException {
        String input = this.bufferedReader.readLine();

        List<Book> allBooks = this.bookRepository.findAll();
        List<Book> containingBooks = new ArrayList<>();

        for (Book allBook : allBooks) {
            String title = allBook.getTitle();

            if (title.toLowerCase().contains(input.toLowerCase())) {
                containingBooks.add(allBook);
            }
        }
        return containingBooks;
    }

    @Override
    public List<Book> bookTitleSearch() throws IOException {

        String input = this.bufferedReader.readLine();
        List<Author> authors = this.authorRepository.findAllByLastNameStartingWith(input);

        List<Book> books = this.bookRepository.findAllByAuthorIn(authors);

        return books;

    }

    @Override
    public Integer countAllByTitle() throws IOException {
        int num = Integer.parseInt(this.bufferedReader.readLine());

        Integer counter = this.bookRepository.countAllByTitle(num);

        return counter;
    }

}
