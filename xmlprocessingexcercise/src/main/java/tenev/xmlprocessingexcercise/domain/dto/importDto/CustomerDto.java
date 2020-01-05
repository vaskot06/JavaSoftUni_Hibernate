package tenev.xmlprocessingexcercise.domain.dto.importDto;

import tenev.xmlprocessingexcercise.domain.entity.Sale;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDto implements Serializable {

    @XmlAttribute
    private String name;
    @XmlElement(name = "birth-date")
    private Date dateOfBirth;
    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;
    private Set<Sale> sales;

    public CustomerDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
