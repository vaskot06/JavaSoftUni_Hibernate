package tenev.xmlprocessingexcercise.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CustomerService {
    void seedCustomers() throws IOException, JAXBException;
}
