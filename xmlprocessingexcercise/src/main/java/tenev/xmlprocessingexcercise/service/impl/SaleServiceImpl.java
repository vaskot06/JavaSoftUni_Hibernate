package tenev.xmlprocessingexcercise.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.xmlprocessingexcercise.domain.dto.importDto.SaleDto;
import tenev.xmlprocessingexcercise.domain.entity.Car;
import tenev.xmlprocessingexcercise.domain.entity.Customer;
import tenev.xmlprocessingexcercise.domain.entity.Sale;
import tenev.xmlprocessingexcercise.repository.CarRepository;
import tenev.xmlprocessingexcercise.repository.CustomerRepository;
import tenev.xmlprocessingexcercise.repository.SaleRepository;
import tenev.xmlprocessingexcercise.service.SaleService;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final SaleRepository saleRepository;


    @Autowired
    public SaleServiceImpl(CarRepository carRepository, CustomerRepository customerRepository, ModelMapper modelMapper, SaleRepository saleRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.saleRepository = saleRepository;
    }

    @Override
    public void seedSales() {
        SaleDto saleDto = new SaleDto();

        for (int i = 1; i <= this.carRepository.count(); i++) {
            saleDto.setCar(this.carRepository.findById(i).orElse(null));
            // saleDto.setCar(selectRandomCar());
            saleDto.setCustomer(selectRandomCustomer());
            saleDto.setDiscountPercentage(getRandomDiscount());
            Sale sale = this.modelMapper.map(saleDto, Sale.class);
            this.saleRepository.saveAndFlush(sale);
        }

    }

    private Customer selectRandomCustomer() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) this.customerRepository.count() - 1) + 1;
        Customer customer = this.customerRepository.findById(randomNumber).orElse(null);
        return customer;
    }

    private Car selectRandomCar() {
        Random random = new Random();
        int randomNumber = random.nextInt((int) this.carRepository.count() - 1) + 1;
        Car car = this.carRepository.findById(randomNumber).orElse(null);
        return car;
    }

    private BigDecimal getRandomDiscount() {
        Random random = new Random();

        BigDecimal number;

        while (true) {
            double bound = random.nextInt(50);
            if (bound == 5 || bound == 10 || bound == 15 || bound == 20 || bound == 30 || bound == 40 || bound == 50 || bound == 0) {
                number = BigDecimal.valueOf(bound / 100);
                break;
            }
        }
        return number;
    }
}
