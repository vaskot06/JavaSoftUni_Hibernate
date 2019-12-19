package tenev.gamestore.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import tenev.gamestore.domain.dtos.*;
import tenev.gamestore.services.GameService;
import tenev.gamestore.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class GameStoreController implements CommandLineRunner {

    private UserService userService;
    private GameService gameService;
    private ModelMapper modelMapper;

    @Autowired
    public GameStoreController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            String[] params = bufferedReader.readLine().split("\\|");

            if ("RegisterUser".equals(params[0])) {
                String email = params[1];
                String password = params[2];
                String confirmPassword = params[3];
                String fullName = params[4];

                UserRegisterDto userRegisterDto = new UserRegisterDto(email, password, confirmPassword, fullName);

                System.out.println(this.userService.registerUser(userRegisterDto));
            } else if ("LoginUser".equals(params[0])) {
                String email = params[1];
                String password = params[2];

                UserLoginDto userLoginDto = new UserLoginDto(email, password);

                System.out.println(this.userService.loginUser(userLoginDto));
            } else if ("Logout".equals(params[0])) {
                System.out.println(this.userService.logoutUser());
            } else if ("AddGame".equals(params[0])) {
                String title = params[1];
                BigDecimal price = new BigDecimal(params[2]);
                Double size = Double.parseDouble(params[3]);
                String trailer = params[4];
                String thumbnailURL = params[5];
                String description = params[6];
                LocalDate releaseDate = LocalDate.parse(params[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                AddGameDto addGameDto = new AddGameDto(title, price, size, trailer, thumbnailURL, description, releaseDate);
                System.out.println(this.gameService.addGame(addGameDto));
            } else if ("EditGame".equals(params[0])) {
                Integer id = Integer.parseInt(params[1]);
                BigDecimal price = new BigDecimal(params[2]);
                EditGameDto editGameDto = new EditGameDto(id, price);
                System.out.println(this.gameService.editGame(editGameDto));
            } else if ("DeleteGame".equals(params[0])) {
                Integer id = Integer.parseInt(params[1]);
                DeleteGameDto deleteGameDto = new DeleteGameDto(id);
                System.out.println(this.gameService.deleteGame(deleteGameDto));
            }

        }
    }
}
