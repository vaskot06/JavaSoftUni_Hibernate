package tenev.jsonexercise.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.jsonexercise.domain.entity.Category;
import tenev.jsonexercise.domain.view.CategoryView;
import tenev.jsonexercise.domain.view.ProductView;
import tenev.jsonexercise.domain.entity.Product;
import tenev.jsonexercise.domain.entity.User;
import tenev.jsonexercise.repository.CategoryRepository;
import tenev.jsonexercise.repository.ProductRepository;
import tenev.jsonexercise.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JsonExports {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public JsonExports(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    public List<ProductView> productsInRange() {

        Set<Product> products = this.productRepository
                .getAllByPriceGreaterThanAndPriceLessThanAndBuyerIsNullOrderByPriceAsc(new BigDecimal(500), new BigDecimal(1000));

        Converter<User, String> nameConverter =
                src -> src.getSource().getFirstName() == null ? src.getSource().getLastName() :
                        src.getSource().getFirstName() + " " + src.getSource().getLastName();

        TypeMap<Product, ProductView> typeMap =
                this.modelMapper.createTypeMap(Product.class, ProductView.class)
                        .addMappings(mapper -> mapper.using(nameConverter).map(Product::getSeller, ProductView::setSellerFullName));

        return products.stream().map(typeMap::map).collect(Collectors.toList());
    }

//    public List<UserView> soldProducts(){
//
//        List<User> users = this.userRepository.findAllByProductsSoldNotNullOrderByLastNameDescFirstNameDesc();
//        List<UserView> userViews = new ArrayList<>();
//
//
//        Converter<User, String> nameConverter =
//                src -> src.getSource().getFirstName() == null ? src.getSource().getLastName() :
//                        src.getSource().getFirstName() + " " + src.getSource().getLastName();
//
//        TypeMap<Product, ProductView> typeMap =
//                this.modelMapper.createTypeMap(Product.class, ProductView.class)
//                        .addMappings(mapper -> mapper.using(nameConverter).map(Product::getBuyer, ProductView::setSellerFullName));
//
//
//        for (User user : users) {
//
//            UserView userView = this.modelMapper.map(user, UserView.class);
//
//
//            List<ProductView> productViews = user.getProductsSold().stream().map(typeMap::map).collect(Collectors.toList());
//
//            for (ProductView productView : productViews) {
//                userView.setProductsSoldName(productView.getName());
//                userView.setProductsSoldPrice(productView.getPrice());
//                userView.setProductsSoldBuyerName(productView.getSellerFullName());
//            }
//
//            userViews.add(userView);
//        }
//
//
//        return userViews;
//    }

    public List<CategoryView> getAllCategories() {

        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryView> categoryViews = new ArrayList<>();

        for (Category category : categories) {


            CategoryView categoryView = new CategoryView();
//
//            TypeMap<Category, CategoryView> typeMap =
//                    this.modelMapper.createTypeMap(Category.class, CategoryView.class)
//            .addMappings(mapper ->mapper.map(Category::getProducts, CategoryView::));

            categoryView.setName(category.getName());
            categoryView.setNumberOfProducts(category.getProducts().size());
            double price = 0;
            String avgPrice;

            Set<Product> products = category.getProducts();
            for (Product product : products) {
                price += product.getPrice().doubleValue();

            }

            double avgPriceInDouble = 0;

            if (products.size() != 0) {
                avgPriceInDouble = price / products.size();
            }

            avgPrice = String.valueOf(avgPriceInDouble);

            categoryView.setAveragePrice(avgPrice);

            categoryView.setTotalRevenue(String.format("%.2f", price));
            categoryViews.add(categoryView);
        }

        return categoryViews;
    }
}