package tenev.jsonexercise.domain.view;

import com.google.gson.annotations.Expose;
import tenev.jsonexercise.domain.entity.Category;
import tenev.jsonexercise.domain.entity.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class ProductView implements Serializable {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private String sellerFullName;

    public ProductView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public void setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
    }
}
