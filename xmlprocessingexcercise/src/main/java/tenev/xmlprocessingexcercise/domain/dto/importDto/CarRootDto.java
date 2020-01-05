package tenev.xmlprocessingexcercise.domain.dto.importDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRootDto {

    @XmlElement(name = "car")
    private List<CarDto> carDtos;

    public CarRootDto() {
    }

    public List<CarDto> getCarDtos() {
        return carDtos;
    }

    public void setCarDtos(List<CarDto> carDtos) {
        this.carDtos = carDtos;
    }
}
