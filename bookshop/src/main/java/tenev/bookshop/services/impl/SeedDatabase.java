package tenev.bookshop.services.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;

@Service
public class SeedDatabase {

    private BookServiceImpl bookService;

    public SeedDatabase(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public void seedDB() {
        try {
            this.bookService.seedBooks();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
