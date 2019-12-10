package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    List<Author> authorsSearch();

    List<Object[]> totalCopiesPerAuthor();
}
