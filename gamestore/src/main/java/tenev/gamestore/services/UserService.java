package tenev.gamestore.services;

import tenev.gamestore.domain.dtos.UserRegisterDto;
import tenev.gamestore.domain.dtos.UserLoginDto;
import tenev.gamestore.domain.entities.User;

import java.util.List;

public interface UserService {

    String registerUser (UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logoutUser();

    List<User> getLoggedInUser();
}
