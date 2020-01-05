package tenev.xmlprocessingexcercise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.xmlprocessingexcercise.domain.dto.importDto.CustomerDto;
import tenev.xmlprocessingexcercise.domain.dto.importDto.CustomerRootDto;
import tenev.xmlprocessingexcercise.domain.entity.Customer;
import tenev.xmlprocessingexcercise.repository.CustomerRepository;
import tenev.xmlprocessingexcercise.service.CustomerService;
import tenev.xmlprocessingexcercise.parsers.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final static String CUSTOMER_CONTENT = "C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\Hibernate\\6.SPRING_DATA_ADVANCED_QUERING\\xmlprocessingexcercise\\src\\main\\resources\\xml\\customers.xml";

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void seedCustomers() throws IOException, JAXBException {

        CustomerRootDto list = this.xmlParser.read(CustomerRootDto.class, CUSTOMER_CONTENT);
        List<CustomerDto> customerDtos = list.getCustomerDtos();

        for (CustomerDto customerDto : customerDtos) {
            Customer customer = this.modelMapper.map(customerDto, Customer.class);
            this.customerRepository.saveAndFlush(customer);
        }

    }
}
