package tenev.jsonexercise.web.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import tenev.jsonexercise.domain.dto.CategorySeedDto;
import tenev.jsonexercise.domain.dto.ProductSeedDto;
import tenev.jsonexercise.domain.entity.User;
import tenev.jsonexercise.domain.view.ProductView;
import tenev.jsonexercise.domain.dto.UserSeedDto;
import tenev.jsonexercise.domain.view.UserView;
import tenev.jsonexercise.service.CategoryService;
import tenev.jsonexercise.service.ProductService;
import tenev.jsonexercise.service.UserService;
import tenev.jsonexercise.service.impl.JsonExports;
import tenev.jsonexercise.util.FileUtil;

import java.io.IOException;
import java.util.List;

@Controller
@Transactional
public class ProductShopController implements CommandLineRunner {

    private final static String USER_JSON_INPUT = "C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\Hibernate\\6.SPRING_DATA_ADVANCED_QUERING\\jsonexercise\\src\\main\\resources\\json\\users.json";
    private final static String PRODUCT_JSON_INPUT = "C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\Hibernate\\6.SPRING_DATA_ADVANCED_QUERING\\jsonexercise\\src\\main\\resources\\json\\products.json";
    private final static String CATEGORY_JSON_INPUT = "C:\\Users\\Vasil\\Desktop\\SoftUni\\Java DB\\Hibernate\\6.SPRING_DATA_ADVANCED_QUERING\\jsonexercise\\src\\main\\resources\\json\\categories.json";


    private final Gson gson;
    private final FileUtil fileUtil;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final JsonExports jsonExports;

    @Autowired
    public ProductShopController(Gson gson, FileUtil fileUtil, UserService userService, ProductService productService, CategoryService categoryService, JsonExports jsonExports) {
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.jsonExports = jsonExports;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedUser();
//        seedCategory();
//        seedProduct();


        //productsInRange();


        String content = this.gson.toJson(this.jsonExports.getAllCategories());

        System.out.println(content);

    }

    public void seedUser() throws IOException {
        String content = this.fileUtil.fileContent(USER_JSON_INPUT);
        UserSeedDto[] userSeedDtos = this.gson.fromJson(content, UserSeedDto[].class);
        this.userService.seedUsers(userSeedDtos);
    }

    public void seedProduct() throws IOException {
        String content = this.fileUtil.fileContent(PRODUCT_JSON_INPUT);
        ProductSeedDto[] seedDtos = this.gson.fromJson(content, ProductSeedDto[].class);
        this.productService.seedProduct(seedDtos);
    }

    public void seedCategory() throws IOException {
        String content = this.fileUtil.fileContent(CATEGORY_JSON_INPUT);
        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(content, CategorySeedDto[].class);
        this.categoryService.seedCategory(categorySeedDtos);
    }

    public void productsInRange() {

        List<ProductView> productView = this.jsonExports.productsInRange();
        String content = this.gson.toJson(productView);

        System.out.println(content);

    }
}
