package com.domain;

import com.domain.controler.AppController;
import com.domain.entities.Ingredient;
import com.domain.entities.Shampoo;
import com.domain.repositories.IngredientRepository;
import com.domain.repositories.LabelRepository;
import com.domain.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@Component
@Transactional
public class Application implements CommandLineRunner {

    private IngredientRepository ingredientRepository;
    private LabelRepository labelRepository;
    private ShampooRepository shampooRepository;
    private AppController appController;

    @Autowired
    public Application(IngredientRepository ingredientRepository,
                       LabelRepository labelRepository,
                       ShampooRepository shampooRepository,
                       AppController appController) {
        this.ingredientRepository = ingredientRepository;
        this.labelRepository = labelRepository;
        this.shampooRepository = shampooRepository;
        this.appController = appController;
    }

    @Override
    public void run(String... args) throws Exception {

        // firstTask();
        // secondTask();
        // thirdTask();
        // fourthTask();
        //   fifthTask();
        //   sixthTask();
        //seventhTask();
        // eightTask();
        ninthTask();
    }

    public void firstTask() throws IOException {
        List<Shampoo> shampoos = appController.selectShampooBySize();
        for (Shampoo shampoo : shampoos) {
            System.out.println(shampoo.getBrand() + " " + shampoo.getPrice() + "lv.");
        }
    }

    public void secondTask() throws IOException {
        List<Shampoo> shampoos = appController.selectShampooBySizeOrLabel();
        for (Shampoo shampoo : shampoos) {
            System.out.println(shampoo.getBrand() + " " + shampoo.getPrice() + "lv.");
        }
    }


    public void thirdTask() throws IOException {
        List<Shampoo> shampoos = appController.selectShampoosByPrice();
        for (Shampoo shampoo : shampoos) {
            System.out.println(shampoo.getBrand() + " " + shampoo.getPrice() + "lv.");
        }
    }

    private void fourthTask() throws IOException {
        List<Ingredient> ingredients = appController.selectIngredientsByName();

        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName());
        }
    }

    private void fifthTask() throws IOException {
        List<Ingredient> ingredients = appController.selectIngredientsByNamesUsingList();

        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName());
        }
    }

    private void sixthTask() throws IOException {
        BigDecimal result = BigDecimal.valueOf(appController.countShampoosByPrice());
        System.out.println(result.toString());
    }

    private void seventhTask() throws IOException {

        List<Shampoo> shampoos = this.appController.selectShampoosByIngredients();

        for (Shampoo shampoo : shampoos) {
            System.out.println(shampoo.getBrand());
        }
    }

    private void eightTask() throws IOException {

        List<Shampoo> shampoos = this.appController.selectShampoosByIngredientsCount();

        for (Shampoo shampoo : shampoos) {
            System.out.println(shampoo.getBrand());
        }
    }

    private void ninthTask() throws IOException {

        List<Shampoo> shampoos = this.appController.selectShampoosAndIngredientsPrice();

        for (Shampoo shampoo : shampoos) {
            Set<Ingredient> ingredients = shampoo.getIngredients();
            BigDecimal price = BigDecimal.valueOf(0);
            for (Ingredient ingredient : ingredients) {
                price = price.add(ingredient.getPrice());
            }
            System.out.println(shampoo.getBrand() + " " + price);
        }

    }
}
