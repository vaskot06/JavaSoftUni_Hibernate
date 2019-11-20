package entities;


import javax.persistence.*;

@Entity
@Table(name = "billing_details")
public class BillingDetail extends BaseEntity {

    @Column(name = "billing_detail")
    private String billingDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public BillingDetail() {
    }


    public String getBillingDetail() {
        return billingDetail;
    }

    public void setBillingDetail(String billingDetail) {
        this.billingDetail = billingDetail;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
