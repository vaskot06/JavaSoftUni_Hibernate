package tenev.xmlprocessingexcercise.domain.dto.exportDto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportDto {

    @XmlAttribute
    private Integer id;
    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private Long travelledKm;

    public CarExportDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
