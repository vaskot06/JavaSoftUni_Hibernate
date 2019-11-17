package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   @Column
   private int id;

   @Column
   private String name;

   @Column
   private double quantity;

   @Column
   private BigDecimal price;


   @OneToMany(mappedBy = "product", targetEntity = Sale.class,
   fetch = FetchType.LAZY,
   cascade = CascadeType.ALL)
   private Set<Sale>  sales;

    public Product() {
    }

    public Product(String name, double quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
