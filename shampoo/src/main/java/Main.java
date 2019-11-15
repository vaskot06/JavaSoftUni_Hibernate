import entities.ingredients.*;
import entities.labels.BasicLabel;
import entities.shampoos.BasicShampoo;
import entities.shampoos.FreshNuke;
import entities.shampoos.PinkPanther;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shampoo");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        BasicIngredient am = new AmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();
        BasicIngredient strawberry = new Strawberry();

        BasicShampoo shampoo = new FreshNuke();
        BasicShampoo pinkShampoo = new PinkPanther();


        shampoo.getIngredients().add(am);
        shampoo.getIngredients().add(mint);
        shampoo.getIngredients().add(nettle);

        pinkShampoo.getIngredients().add(am);
        pinkShampoo.getIngredients().add(strawberry);

        entityManager.persist(shampoo);
        entityManager.persist(pinkShampoo);

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
