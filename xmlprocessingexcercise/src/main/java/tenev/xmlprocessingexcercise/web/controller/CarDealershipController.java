package tenev.xmlprocessingexcercise.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import tenev.xmlprocessingexcercise.service.*;

import javax.transaction.Transactional;

@Controller
@Transactional
public class CarDealershipController implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final XmlExports xmlExports;

    @Autowired
    public CarDealershipController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService, XmlExports xmlExports) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.xmlExports = xmlExports;
    }


    @Override
    public void run(String... args) throws Exception {

//        this.supplierService.seedSupplier();
//        this.partService.seedParts();
//        this.carService.seedCars();
//        this.customerService.seedCustomers();
//        this.saleService.seedSales();

        //this.xmlExports.orderedCustomers();
        // this.xmlExports.carsFromMakeToyota();
        //this.xmlExports.localSuppliers();

        this.xmlExports.carsWithTheirListsOfParts();

    }
}
