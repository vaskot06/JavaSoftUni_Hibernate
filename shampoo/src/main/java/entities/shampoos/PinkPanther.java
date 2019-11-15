package entities.shampoos;

import entities.labels.BasicLabel;
import entities.size.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "PP")
public class PinkPanther extends BasicShampoo {

    private final static String BRAND = "Pink Panther";
    private final static BigDecimal PRICE = new BigDecimal(8.50);

    public PinkPanther() {
        super(BRAND,
                PRICE,
                Size.MEDIUM,
                new BasicLabel("Pink Panther", "It’s made of Lavender and Nettle"));
    }
}
