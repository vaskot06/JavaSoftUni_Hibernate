package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   @Column
   private int id;

   @Column
   private String name;

   @Column
   private String email;

   @Column(name = "credit_card_number")
   private String creditCardnumber;


   @OneToMany(mappedBy = "customer",
           targetEntity = Sale.class,
           fetch = FetchType.LAZY,
           cascade = CascadeType.ALL)
   private Set<Sale> sales;

    public Customer() {
    }

    public Customer(String name, String email, String creditCardnumber) {
        this.name = name;
        this.email = email;
        this.creditCardnumber = creditCardnumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardnumber() {
        return creditCardnumber;
    }

    public void setCreditCardnumber(String creditCardnumber) {
        this.creditCardnumber = creditCardnumber;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
