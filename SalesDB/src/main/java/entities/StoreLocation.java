package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_location")
public class StoreLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private int id;

    @Column(name = "location_name")
    private String locationName;



    @OneToMany(mappedBy = "storeLocation",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    Set<Sale> sales;

    public StoreLocation() {
    }

    public StoreLocation(String locationName) {
        this.locationName = locationName;
    }
}
