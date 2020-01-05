package tenev.xmlprocessingexcercise.domain.dto.exportDto;

import tenev.xmlprocessingexcercise.domain.entity.Part;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierExportDto {

    @XmlAttribute
    private Integer id;
    @XmlAttribute
    private String name;
    @XmlTransient
    private List<Part> parts;
    @XmlAttribute(name = "parts-count")
    private Integer partsCount;


    public SupplierExportDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public Integer getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}
