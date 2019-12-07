package com.domain.repositories;

import com.domain.entities.Ingredient;
import com.domain.entities.Shampoo;
import com.domain.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelIdOrderByPriceAsc(Size size, Long id);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    Integer countAllByPriceLessThan(BigDecimal price);


    @Query(value = "SELECT s FROM Shampoo AS s JOIN s.ingredients AS i WHERE i IN :ingredients")
    List<Shampoo> findByIngredientsIn(@Param(value = "ingredients") List<Ingredient> ingredients);

    @Query(value = "select s from Shampoo as s join s.ingredients as i group by s.brand having count(i) < :number")
    List<Shampoo> findAllByIngredientsLessThan(@Param(value = "number") Long number);

    @Query(value = "select s from Shampoo as s join s.ingredients as i group by s")
    List<Shampoo> findAllByIngredients(List<Ingredient> ingredients);


//    select s.brand FROM shampoos as s
//    join shampoos_ingredients si on s.id = si.shampoo_id
//    join ingredients i on si.ingredient_id = i.id
//    group by si.shampoo_id
//    having (SELECT count(si.ingredient_id)) < ?
}
