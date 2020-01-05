package tenev.xmlprocessingexcercise.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tenev.xmlprocessingexcercise.util.FileUtilImpl;
import tenev.xmlprocessingexcercise.util.ValidatorUtilImpl;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public FileUtilImpl fileUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidatorUtilImpl validatorUtil() {
        return new ValidatorUtilImpl(validator());
    }

}


