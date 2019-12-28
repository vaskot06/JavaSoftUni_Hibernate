package tenev.jsonexercise.domain.dto;

import com.google.gson.annotations.Expose;
import tenev.jsonexercise.domain.entity.Product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CategorySeedDto implements Serializable {
    @Expose
    private String name;
    @Expose
    private Set<Product> products;

    public CategorySeedDto() {
    }

    @NotNull
    @Size(min = 3, max = 15, message = "Name is not in the correct format")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
