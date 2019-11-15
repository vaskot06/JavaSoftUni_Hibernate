package entities.shampoos;

import entities.labels.BasicLabel;
import entities.size.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FN")
public class FreshNuke extends BasicShampoo {

    private final static String BRAND = "Fresh Nuke";
    private final static BigDecimal PRICE = new BigDecimal(9.33);


    public FreshNuke() {
        super(BRAND,
                PRICE,
                Size.LARGE,
                new BasicLabel("Fresh Nuke", "It’s made of Mint, Nettle and Ammonium Chloride"));
    }
}
