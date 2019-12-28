package tenev.jsonexercise.domain.view;

import com.google.gson.annotations.Expose;
import tenev.jsonexercise.domain.entity.Product;

import java.math.BigDecimal;
import java.util.Set;

public class UserView {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String productsSoldName;
    @Expose
    private BigDecimal productsSoldPrice;
    @Expose
    private String productsSoldBuyerName;


    public UserView() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProductsSoldName() {
        return productsSoldName;
    }

    public void setProductsSoldName(String productsSoldName) {
        this.productsSoldName = productsSoldName;
    }

    public BigDecimal getProductsSoldPrice() {
        return productsSoldPrice;
    }

    public void setProductsSoldPrice(BigDecimal productsSoldPrice) {
        this.productsSoldPrice = productsSoldPrice;
    }

    public String getProductsSoldBuyerName() {
        return productsSoldBuyerName;
    }

    public void setProductsSoldBuyerName(String productsSoldBuyerName) {
        this.productsSoldBuyerName = productsSoldBuyerName;
    }
}
