package tenev.bookshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.bookshop.entities.Author;
import tenev.bookshop.repositories.AuthorRepository;
import tenev.bookshop.services.AuthorService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\Hibernate\\5.SPRING DATA INTRO\\bookshop\\src\\main\\resources\\files\\authors.txt"));

        String line = bufferedReader.readLine();

        while (line != null) {

            String[] names = line.split(" ");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);
            author.setBooks(new HashSet<>());

            this.authorRepository.saveAndFlush(author);

            line = bufferedReader.readLine();
        }

    }
}
