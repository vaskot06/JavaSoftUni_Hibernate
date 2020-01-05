package tenev.xmlprocessingexcercise.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    private Car car;
    private Customer customer;
    private BigDecimal discountPercentage;

    public Sale() {
    }

    @OneToOne
    @JoinColumn(name = "car_id",
                referencedColumnName = "id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "discount")
    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
