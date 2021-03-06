package tenev.xmlprocessingexcercise.domain.dto.importDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierRootDto implements Serializable {

    @XmlElement(name = "supplier")
    private List<SupplierDto> supplierDtos;

    public SupplierRootDto() {
    }

    public List<SupplierDto> getSupplierDtos() {
        return supplierDtos;
    }

    public void setSupplierDtos(List<SupplierDto> supplierDtos) {
        this.supplierDtos = supplierDtos;
    }
}
