package tenev.xmlprocessingexcercise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.xmlprocessingexcercise.domain.dto.importDto.SupplierRootDto;
import tenev.xmlprocessingexcercise.domain.entity.Supplier;
import tenev.xmlprocessingexcercise.repository.SupplierRepository;
import tenev.xmlprocessingexcercise.service.SupplierService;
import tenev.xmlprocessingexcercise.parsers.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private static final String SUPPLIER_FILE_PATH = "C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\Hibernate\\6.SPRING_DATA_ADVANCED_QUERING\\xmlprocessingexcercise\\src\\main\\resources\\xml\\suppliers.xml";
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSupplier() {

        try {
            SupplierRootDto list =  xmlParser.read(SupplierRootDto.class, SUPPLIER_FILE_PATH);
            list.getSupplierDtos().stream()
                    .map(s->this.modelMapper.map(s, Supplier.class))
                    .forEach(this.supplierRepository::saveAndFlush);

        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
