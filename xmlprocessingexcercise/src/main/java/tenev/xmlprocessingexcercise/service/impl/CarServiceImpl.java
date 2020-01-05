package tenev.xmlprocessingexcercise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.xmlprocessingexcercise.domain.dto.importDto.CarDto;
import tenev.xmlprocessingexcercise.domain.dto.importDto.CarRootDto;
import tenev.xmlprocessingexcercise.domain.entity.Car;
import tenev.xmlprocessingexcercise.domain.entity.Part;
import tenev.xmlprocessingexcercise.repository.CarRepository;
import tenev.xmlprocessingexcercise.repository.PartRepository;
import tenev.xmlprocessingexcercise.service.CarService;
import tenev.xmlprocessingexcercise.parsers.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CarServiceImpl implements CarService {

    private static final String CAR_CONTENT = "C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\Hibernate\\6.SPRING_DATA_ADVANCED_QUERING\\xmlprocessingexcercise\\src\\main\\resources\\xml\\cars.xml";
    private final XmlParser xmlParser;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(XmlParser xmlParser, PartRepository partRepository, ModelMapper modelMapper, CarRepository carRepository) {
        this.xmlParser = xmlParser;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }


    @Override
    public void seedCars() throws IOException, JAXBException {

        CarRootDto list = this.xmlParser.read(CarRootDto.class, CAR_CONTENT);
        List<CarDto> carDtos = list.getCarDtos();

        for (CarDto carDto : carDtos) {
            carDto.setParts(randomParts());

            Car car = this.modelMapper.map(carDto, Car.class);
            this.carRepository.saveAndFlush(car);
        }

    }

    private List<Part> randomParts() {
        List<Part> parts = new ArrayList<>();
        Random randomNumber10to20 = new Random();
        Random randomPart = new Random();
        int randomNumber = randomNumber10to20.nextInt(20 - 10) + 10;

        for (int i = 0; i < randomNumber; i++) {
            parts.add(this.partRepository.findById(randomPart.nextInt((int) (this.partRepository.count() + 1) - 1)).orElse(null));
        }

        return parts;
    }

}
