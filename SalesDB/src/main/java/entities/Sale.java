package entities;

import jdk.jfr.Name;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private int id;


    @ManyToOne(targetEntity = Product.class,
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;



    @ManyToOne(targetEntity = Customer.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;



    @ManyToOne(targetEntity = StoreLocation.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
   @JoinColumn(name = "store_location_id", referencedColumnName = "id")
    private StoreLocation storeLocation;


    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
