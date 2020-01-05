package tenev.xmlprocessingexcercise.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CarService {
    void seedCars() throws IOException, JAXBException;
}
