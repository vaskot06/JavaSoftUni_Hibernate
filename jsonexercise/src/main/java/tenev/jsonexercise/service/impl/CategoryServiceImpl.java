package tenev.jsonexercise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.jsonexercise.domain.dto.CategorySeedDto;
import tenev.jsonexercise.domain.entity.Category;
import tenev.jsonexercise.domain.entity.Product;
import tenev.jsonexercise.repository.CategoryRepository;
import tenev.jsonexercise.repository.ProductRepository;
import tenev.jsonexercise.service.CategoryService;
import tenev.jsonexercise.util.ValidatorUtil;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final ValidatorUtil validatorUtil;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ValidatorUtil validatorUtil, ProductRepository productRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.validatorUtil = validatorUtil;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategory(CategorySeedDto[] categorySeedDto) {

        for (CategorySeedDto seedDto : categorySeedDto) {
//            Set<Product> products = new HashSet<>();
//
//            for (int i = 0; i < 3; i++) {
//                products.add(randomProduct());
//            }
//
//            seedDto.setProducts(products);
            if (!this.validatorUtil.isValid(seedDto)) {
                this.validatorUtil.violations(seedDto).forEach(v -> System.out.println(v.getMessage()));
                continue;
            }

            Category category = this.modelMapper.map(seedDto, Category.class);
            this.categoryRepository.saveAndFlush(category);
        }
    }

    public Product randomProduct() {
        Random random = new Random();

        int id = random.nextInt((int) (this.productRepository.count() - 1) + 1);
        Product product = this.productRepository.findById(id).get();
        return product;
    }
}