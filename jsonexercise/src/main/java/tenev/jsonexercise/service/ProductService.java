package tenev.jsonexercise.service;

import tenev.jsonexercise.domain.dto.ProductSeedDto;
import tenev.jsonexercise.domain.entity.User;

public interface ProductService {
    void seedProduct(ProductSeedDto[] productSeedDto);

    User randomSeller();
    User randomBuyer();
}
