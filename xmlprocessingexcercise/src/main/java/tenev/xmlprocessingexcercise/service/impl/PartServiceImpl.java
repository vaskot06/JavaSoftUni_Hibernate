package tenev.xmlprocessingexcercise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.xmlprocessingexcercise.domain.dto.importDto.PartDto;
import tenev.xmlprocessingexcercise.domain.dto.importDto.PartRootDto;
import tenev.xmlprocessingexcercise.domain.entity.Part;
import tenev.xmlprocessingexcercise.domain.entity.Supplier;
import tenev.xmlprocessingexcercise.repository.PartRepository;
import tenev.xmlprocessingexcercise.repository.SupplierRepository;
import tenev.xmlprocessingexcercise.service.PartService;
import tenev.xmlprocessingexcercise.parsers.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {

    private final XmlParser xmlParser;
    private final static String PARTS_CONTENT = "C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\Hibernate\\6.SPRING_DATA_ADVANCED_QUERING\\xmlprocessingexcercise\\src\\main\\resources\\xml\\parts.xml";
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(XmlParser xmlParser, SupplierRepository supplierRepository, ModelMapper modelMapper, PartRepository partRepository) {
        this.xmlParser = xmlParser;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.partRepository = partRepository;
    }

    @Override
    public void seedParts() throws IOException, JAXBException {
        PartRootDto list = this.xmlParser.read(PartRootDto.class, PARTS_CONTENT);
        List<PartDto> partDtos = list.getPartDtos();

        for (PartDto partDto : partDtos) {
            partDto.setSupplier(randomSupplier());

            Part part = this.modelMapper.map(partDto, Part.class);
            this.partRepository.saveAndFlush(part);
        }
    }

    private Supplier randomSupplier() {
        Random random = new Random();

        Supplier supplier =
                this.supplierRepository.findById(random.nextInt((int) ((this.supplierRepository.count() - 1) + 1)))
                        .orElse(null);

        return supplier;
    }
}
