package tenev.jsonexercise.domain.view;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryView {

    @Expose
    private String name;
    @Expose
    private Integer numberOfProducts;
    @Expose
    private String averagePrice;
    @Expose
    private String totalRevenue;

    public CategoryView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Integer numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
