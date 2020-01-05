package tenev.xmlprocessingexcercise.domain.dto.exportDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierExportRootDto {

    @XmlElement(name = "supplier")
    private List<SupplierExportDto> list;

    public SupplierExportRootDto() {
    }

    public List<SupplierExportDto> getList() {
        return list;
    }

    public void setList(List<SupplierExportDto> list) {
        this.list = list;
    }
}
