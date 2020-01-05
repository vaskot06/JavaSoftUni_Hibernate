package tenev.xmlprocessingexcercise.domain.dto.exportDto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportDtoWithParts {

    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private Long travelledKm;
    @XmlElement(name = "parts")
    private PartExportRootDto parts;

    public CarExportDtoWithParts() {
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

    public PartExportRootDto getParts() {
        return parts;
    }

    public void setParts(PartExportRootDto parts) {
        this.parts = parts;
    }
}
