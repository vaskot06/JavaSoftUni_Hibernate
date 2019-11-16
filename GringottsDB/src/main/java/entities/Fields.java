package entities;

import com.sun.istack.NotNull;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "gringotts")
public class Fields {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name",
            length = 50)
    private String firstName;

    @Column(name = "last_name",
            length = 60)
    private String lastName;

    @Column(length = 1000)
    private String notes;


    @Column
    @Min(0)
    private int age;


    @Column(name = "magic_wand_creator", length = 100)
    private String magicWantCreator;

    @Column(name = "magic_wand_size")
    @Min(1)
    private int magicWandSize;

    @Column(name = "deposit_group", length = 50)
    private String depositGroup;

    @Column(name = "deposit_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depositStartTime;

    @Column(name = "deposit_amount")
    private double depositAmount;

    @Column(name = "deposit_interest")
    private double depositInterest;

    @Column(name = "deposit_charge")
    private double depositCharge;

    @Column(name = "deposit_expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depositExpirationDate;

    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;

}
