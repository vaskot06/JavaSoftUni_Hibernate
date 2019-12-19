package tenev.gamestore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenev.gamestore.domain.dtos.UserRegisterDto;
import tenev.gamestore.domain.dtos.UserLoginDto;
import tenev.gamestore.domain.entities.Role;
import tenev.gamestore.domain.entities.User;
import tenev.gamestore.repositories.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private List<User> loggedInUser;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
        this.loggedInUser = new ArrayList<>();
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return "Password and confirm password fields are different. Please make sure they match.";
        }

        Optional<User> userToCheck = this.userRepository.findByEmail(userRegisterDto.getEmail());

        if (userToCheck.isPresent()) {
            return "Email already exists in the system. Please try again.";
        }

        StringBuilder sb = new StringBuilder();
        User user = this.modelMapper.map(userRegisterDto, User.class);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);


        if (this.userRepository.findAll().isEmpty()) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {
            this.userRepository.saveAndFlush(user);
            sb.append(String.format("%s was registered!", user.getFullName()));
        }

        return sb.toString();
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        StringBuilder sb = new StringBuilder();

        if (!loggedInUser.isEmpty()) {
            return sb.append("There's already a logged user. Please try again later").toString();
        }

        User user = this.userRepository.findByEmail(userLoginDto.getEmail()).orElse(null);

        if (user == null) {
            return sb.append("Incorrect email!").toString();
        }

        if (!user.getPassword().equals(userLoginDto.getPassword())) {
            return sb.append("Incorrect username / password!").toString();
        }

        this.loggedInUser.add(user);

        sb.append(String.format("Successfully logged in %s.", user.getFullName())).append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public String logoutUser() {
        StringBuilder stringBuilder = new StringBuilder();

        if (this.loggedInUser.isEmpty()) {
            return stringBuilder.append("Cannot log out. No user was logged in.").toString();
        }

        User user = null;

        for (User loggedUser : loggedInUser) {
            user = loggedUser;
        }

        this.loggedInUser.clear();

        stringBuilder.append(String.format("User %s successfully logged out.", user.getFullName()));

        return stringBuilder.toString();
    }

    public List<User> getLoggedInUser() {
        return loggedInUser;
    }
}
