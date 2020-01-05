package tenev.xmlprocessingexcercise.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.xmlprocessingexcercise.domain.dto.exportDto.*;
import tenev.xmlprocessingexcercise.domain.entity.Car;
import tenev.xmlprocessingexcercise.domain.entity.Customer;
import tenev.xmlprocessingexcercise.domain.entity.Part;
import tenev.xmlprocessingexcercise.domain.entity.Supplier;
import tenev.xmlprocessingexcercise.repository.CarRepository;
import tenev.xmlprocessingexcercise.repository.CustomerRepository;
import tenev.xmlprocessingexcercise.repository.PartRepository;
import tenev.xmlprocessingexcercise.repository.SupplierRepository;
import tenev.xmlprocessingexcercise.parsers.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlExports {
    private final XmlParser xmlParser;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;


    @Autowired
    public XmlExports(XmlParser xmlParser, CustomerRepository customerRepository, ModelMapper modelMapper, CarRepository carRepository, SupplierRepository supplierRepository, PartRepository partRepository) {
        this.xmlParser = xmlParser;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
    }

    public void orderedCustomers() throws IOException, JAXBException {
        List<Customer> customers = this.customerRepository.findAll();
        List<CustomerExportDto> customerExportDtos = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerExportDto customerExportDto = this.modelMapper.map(customer, CustomerExportDto.class);
            customerExportDtos.add(customerExportDto);
        }

        CustomerExportRootDto customerExportRootDto = new CustomerExportRootDto();
        customerExportRootDto.setCustomerExportDtos(customerExportDtos);

        String content = xmlParser.write(customerExportRootDto);
        System.out.println(content);

    }

    public void carsFromMakeToyota() throws IOException, JAXBException {

        List<Car> cars = this.carRepository.findAllByMakeEqualsOrderByModelAscTravelledKmDesc("Toyota");
        List<CarExportDto> carExportDtos = new ArrayList<>();

        for (Car car : cars) {
            CarExportDto carExportDto = this.modelMapper.map(car, CarExportDto.class);
            carExportDtos.add(carExportDto);
        }

        CarExportRootDto carExportRootDto = new CarExportRootDto();
        carExportRootDto.setCarExportDtoList(carExportDtos);


        String content = xmlParser.write(carExportRootDto);
        System.out.println(content);
    }

    public void localSuppliers() throws IOException, JAXBException {

        List<Supplier> suppliers = this.supplierRepository.findAllByImporterIsFalse();
        List<SupplierExportDto> supplierExportDtos = new ArrayList<>();

        for (Supplier supplier : suppliers) {
            SupplierExportDto supplierExportDto = this.modelMapper.map(supplier, SupplierExportDto.class);
            int countParts = supplierExportDto.getParts().size();
            supplierExportDto.setPartsCount(countParts);
            supplierExportDtos.add(supplierExportDto);
        }

        SupplierExportRootDto supplierExportRootDto = new SupplierExportRootDto();
        supplierExportRootDto.setList(supplierExportDtos);

        String content = xmlParser.write(supplierExportRootDto);
        System.out.println(content);
    }


    public void carsWithTheirListsOfParts() throws IOException, JAXBException {
        List<Car> cars = this.carRepository.findAll();
        List<CarExportDtoWithParts> carExportDtoWithPartsList = new ArrayList<>();

        for (Car car : cars) {
            List<PartExportDto> partExportDtos = new ArrayList<>();
            CarExportDtoWithParts carExportDtoWithParts = this.modelMapper.map(car, CarExportDtoWithParts.class);

            List<Part> parts = car.getParts();

            for (Part part : parts) {
                PartExportDto partExportDto = this.modelMapper.map(part,PartExportDto.class);
                partExportDtos.add(partExportDto);
            }
            PartExportRootDto partExportRootDto = new PartExportRootDto();
            partExportRootDto.setPartExportDtoList(partExportDtos);

            carExportDtoWithParts.setParts(partExportRootDto);

            carExportDtoWithPartsList.add(carExportDtoWithParts);
        }

        CarExportRootDtoWithParts carExportRootDtoWithParts = new CarExportRootDtoWithParts();
        carExportRootDtoWithParts.setList(carExportDtoWithPartsList);

        String content = xmlParser.write(carExportRootDtoWithParts);
        System.out.println(content);

    }

}
