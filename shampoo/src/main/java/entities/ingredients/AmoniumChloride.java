package entities.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "AM")
public class AmoniumChloride extends BasicChemicalIngredient {
    private static final String NAME = "Amonium Chloride";
    private static final BigDecimal PRICE = new BigDecimal(6.12);

    private String formula;

    public AmoniumChloride() {
        super(NAME, PRICE, "NH4CL");
    }

    @Override
    public void setChemicalFormula(String chemicalFormula) {
        this.formula = chemicalFormula;
    }

    @Override
    public String getChemicalFormula() {
        return "NH4Cl";
    }
}
