package tenev.xmlprocessingexcercise.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PartService {

    void seedParts() throws IOException, JAXBException;
}
