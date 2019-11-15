package entities.shampoos;

import entities.labels.BasicLabel;
import entities.size.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FS")
public class FiftyShades extends BasicShampoo {

    private final static String BRAND = "Fifty Shades";
    private final static BigDecimal PRICE = new BigDecimal(6.69);

    public FiftyShades() {
        super(BRAND,
                PRICE,
                Size.SMALL,
                new BasicLabel("Fifty Shades", "It’s made of Strawberry and Nettle"));
    }


}
