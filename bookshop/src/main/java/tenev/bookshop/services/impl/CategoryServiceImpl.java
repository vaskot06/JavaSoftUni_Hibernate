package tenev.bookshop.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.bookshop.entities.Book;
import tenev.bookshop.entities.Category;
import tenev.bookshop.repositories.CategoryRepository;
import tenev.bookshop.services.CategoryService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {


    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategory() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\Hibernate\\5.SPRING DATA INTRO\\bookshop\\src\\main\\resources\\files\\categories.txt"));

        String line = bufferedReader.readLine();

        while (line != null) {

            if (line.isBlank()) {
                line = bufferedReader.readLine();
                continue;
            }

            Category category = new Category();
            category.setName(line);
            category.setBookSet(new HashSet<>());

            this.categoryRepository.saveAndFlush(category);

            line = bufferedReader.readLine();
        }

    }
}
