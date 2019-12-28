package tenev.jsonexercise.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.jsonexercise.domain.dto.ProductSeedDto;
import tenev.jsonexercise.domain.entity.Category;
import tenev.jsonexercise.domain.entity.Product;
import tenev.jsonexercise.domain.entity.User;
import tenev.jsonexercise.repository.CategoryRepository;
import tenev.jsonexercise.repository.ProductRepository;
import tenev.jsonexercise.repository.UserRepository;
import tenev.jsonexercise.service.ProductService;
import tenev.jsonexercise.util.ValidatorUtil;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ValidatorUtil validatorUtil;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ValidatorUtil validatorUtil, UserRepository userRepository, ProductRepository productRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.validatorUtil = validatorUtil;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedProduct(ProductSeedDto[] productSeedDto) {

        for (ProductSeedDto seedDto : productSeedDto) {
            boolean categoryFound = false;
            boolean categoryNull = false;

            seedDto.setSeller(randomSeller());
            seedDto.setBuyer(randomBuyer());
            Set<Category> categories = seedDto.getCategory();
            for (int i = 0; i < 3; i++) {
                if (categories == null) {
                    categories = new HashSet<>();
                }

                Category category = randomCategory();

                if (category == null) {
                    categoryNull = true;
                }

                if (!categories.isEmpty()) {
                    for (Category categoriesSet : categories) {
                        if (!categoryNull){
                            if (categoriesSet.getName().equals(category.getName())) {
                                categoryFound = true;
                                break;
                            }
                        }
                    }
                }

                if (!categoryFound && !categoryNull) {
                    categories.add(category);
                }
            }
            seedDto.setCategory(categories);
            if (!this.validatorUtil.isValid(seedDto)) {
                this.validatorUtil.violations(seedDto).forEach(v -> System.out.println(v.getMessage()));
                continue;
            }

            Product product = this.modelMapper.map(seedDto, Product.class);
            this.productRepository.saveAndFlush(product);
        }
    }

    @Override
    public User randomSeller() {

        Random random = new Random();
        User user = this.userRepository.findById(random.nextInt((int) this.userRepository.count() - 1) + 1).orElse(null);

        return user;
    }

    @Override
    public User randomBuyer() {
        Random random = new Random();

        int id = random.nextInt((int) (this.userRepository.count() - 1) + 1);

        if (id % 3 == 0) {
            return null;
        }

        User user = this.userRepository.findById(id).orElse(null);


        return user;
    }

    public Category randomCategory() {
        Random random = new Random();

        int id = random.nextInt((int) (this.categoryRepository.count() - 1) + 1);
        Category category = this.categoryRepository.findById(id).orElse(null);
        return category;
    }
}

