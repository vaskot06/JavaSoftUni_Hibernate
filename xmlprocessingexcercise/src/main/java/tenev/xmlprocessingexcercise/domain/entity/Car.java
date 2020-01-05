package tenev.xmlprocessingexcercise.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    private String make;
    private String model;
    private Long travelledKm;
    private List<Part> parts;
    private Sale sale;

    public Car() {
    }

    @Column(nullable = false, updatable = false)
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(nullable = false,updatable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance")
    public Long getTravelledKm() {
        return travelledKm;
    }

    public void setTravelledKm(Long travelledKm) {
        this.travelledKm = travelledKm;
    }

    @ManyToMany
    @JoinTable(
            name = "parts_cars",
            joinColumns = @JoinColumn(name = "part_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    @OneToOne(mappedBy = "car")
    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
