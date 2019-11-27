package tenev.bookshop.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public interface BookService {
    void seedBooks() throws IOException, ParseException;
}
