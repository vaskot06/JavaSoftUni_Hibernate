package com.domain.controler;

import com.domain.entities.Ingredient;
import com.domain.entities.Shampoo;
import com.domain.entities.Size;
import com.domain.repositories.IngredientRepository;
import com.domain.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AppController {
    private ShampooRepository shampooRepository;
    private IngredientRepository ingredientRepository;
    private BufferedReader bufferedReader;

    @Autowired
    public AppController(ShampooRepository shampooRepository, IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.ingredientRepository = ingredientRepository;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public List<Shampoo> selectShampooBySize() throws IOException {
        Size size = Size.valueOf(this.bufferedReader.readLine().toUpperCase());
        return this.shampooRepository.findAllBySizeOrderById(size);
    }

    public List<Shampoo> selectShampooBySizeOrLabel() throws IOException {
        Size size = Size.valueOf(this.bufferedReader.readLine().toUpperCase());
        Long id = Long.valueOf(bufferedReader.readLine());
        return this.shampooRepository.findAllBySizeOrLabelIdOrderByPriceAsc(size, id);
    }

    public List<Shampoo> selectShampoosByPrice() throws IOException {
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(this.bufferedReader.readLine()));
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    public List<Ingredient> selectIngredientsByName() throws IOException {
        return this.ingredientRepository.findAllByNameStartingWith(this.bufferedReader.readLine());
    }

    public List<Ingredient> selectIngredientsByNamesUsingList() throws IOException {
        String ingredientName = this.bufferedReader.readLine();
        List<String> ingredientNames = new ArrayList<>();
        while (!ingredientName.equals("result")) {
            ingredientNames.add(ingredientName);

            ingredientName = this.bufferedReader.readLine();
        }
        return this.ingredientRepository.findAllByNameInOrderByPriceAsc(ingredientNames);
    }

    public Integer countShampoosByPrice() throws IOException {
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(this.bufferedReader.readLine()));
        return this.shampooRepository.countAllByPriceLessThan(price);
    }

    public List<Shampoo> selectShampoosByIngredients() throws IOException {

        List<Ingredient> ingredients = selectIngredientsByNamesUsingList();

        List<Shampoo> shampoos = this.shampooRepository.findByIngredientsIn(ingredients);

        return shampoos;
    }

    public List<Shampoo> selectShampoosByIngredientsCount() throws IOException {
        List<Ingredient> ingredients = this.ingredientRepository.findAll();
        Long number = Long.parseLong(this.bufferedReader.readLine());

        List<Shampoo> shampoos = this.shampooRepository.findAllByIngredientsLessThan(number);

        return shampoos;
    }

    public List<Shampoo> selectShampoosAndIngredientsPrice() throws IOException {
        List<Ingredient> ingredients = this.ingredientRepository.findAll();

        List<Shampoo> shampoos = this.shampooRepository.findAllByIngredients(ingredients);

        return shampoos;
    }

}
