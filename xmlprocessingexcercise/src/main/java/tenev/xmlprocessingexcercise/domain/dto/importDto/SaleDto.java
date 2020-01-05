package tenev.xmlprocessingexcercise.domain.dto.importDto;

import tenev.xmlprocessingexcercise.domain.entity.Car;
import tenev.xmlprocessingexcercise.domain.entity.Customer;

import java.math.BigDecimal;

public class SaleDto {

    private Car car;
    private Customer customer;
    private BigDecimal discountPercentage;

    public SaleDto() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
