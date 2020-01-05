package tenev.xmlprocessingexcercise.domain.dto.exportDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportRootDtoWithParts {

    @XmlElement(name = "car")
    private List<CarExportDtoWithParts> list;

    public CarExportRootDtoWithParts() {
    }

    public List<CarExportDtoWithParts> getList() {
        return list;
    }

    public void setList(List<CarExportDtoWithParts> list) {
        this.list = list;
    }
}
