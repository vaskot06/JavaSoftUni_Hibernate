package tenev.xmlprocessingexcercise.domain.dto.importDto;

import tenev.xmlprocessingexcercise.domain.entity.Part;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarDto {


    @XmlElement
    private String make;
    @XmlElement
    private String model;
    @XmlElement(name = "travelled-distance")
    private Long travelledKm;
    private List<Part> parts;


    public CarDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledKm() {
        return travelledKm;
    }

    public void setTravelledKm(Long travelledKm) {
        this.travelledKm = travelledKm;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
