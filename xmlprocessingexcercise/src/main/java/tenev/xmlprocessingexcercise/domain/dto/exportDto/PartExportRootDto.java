package tenev.xmlprocessingexcercise.domain.dto.exportDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartExportRootDto {

    @XmlElement(name = "part")
    private List<PartExportDto> partExportDtoList;

    public PartExportRootDto() {
    }

    public List<PartExportDto> getPartExportDtoList() {
        return partExportDtoList;
    }

    public void setPartExportDtoList(List<PartExportDto> partExportDtoList) {
        this.partExportDtoList = partExportDtoList;
    }
}
