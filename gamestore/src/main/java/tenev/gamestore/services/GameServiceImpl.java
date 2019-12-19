package tenev.gamestore.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tenev.gamestore.domain.dtos.AddGameDto;
import tenev.gamestore.domain.dtos.DeleteGameDto;
import tenev.gamestore.domain.dtos.EditGameDto;
import tenev.gamestore.domain.entities.Game;
import tenev.gamestore.domain.entities.Role;
import tenev.gamestore.domain.entities.User;
import tenev.gamestore.repositories.GameRepository;
import tenev.gamestore.repositories.UserRepository;

import javax.persistence.PreRemove;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;
    private ModelMapper modelMapper;
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String addGame(AddGameDto addGameDto) {

        StringBuilder sb = new StringBuilder();

        if (checkRole().equals("User is not an admin")) {
            return sb.append("User is not an admin").toString();
        }

        Game game = this.modelMapper.map(addGameDto, Game.class);
        Game titleToCheckGame = this.gameRepository.findByTitle(game.getTitle()).orElse(null);

        if (titleToCheckGame != null) {
            String titleToCheck = titleToCheckGame.getTitle();
            if (game.getTitle().equals(titleToCheck)) {
                return sb.append("Game already exists!").toString();
            }
        }


        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Game>> violations = validator.validate(game);

        if (violations.size() != 0) {

            for (ConstraintViolation<Game> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }

        }

        this.gameRepository.saveAndFlush(game);
        sb.append(String.format("Added %s", game.getTitle()));
        List<User> loggedInUser = this.userService.getLoggedInUser();
        User user = null;
        for (User loggedInUserCurrently : loggedInUser) {
            user = loggedInUserCurrently;
        }

        if (user.getGames() == null){
            user.setGames(new HashSet<>());
            user.getGames().add(game);
        }else {
            Set<Game> gameSet = user.getGames();
            gameSet.add(game);
            user.setGames(gameSet);

        }

        this.userRepository.saveAndFlush(user);
        return sb.toString();
    }

    private String checkRole() {

        StringBuilder sb = new StringBuilder();

        List<User> loggedInUserList = this.userService.getLoggedInUser();
        User loggedInUser = loggedInUserList.stream().findFirst().orElse(null);

        if (loggedInUser.getRole() != Role.ADMIN) {
            sb.append("User is not an admin");
        }
        return sb.toString();

    }

    @Override
    public String editGame(EditGameDto editGameDto) {
        StringBuilder sb = new StringBuilder();

        checkRole();

        Integer id = editGameDto.getId();
        Game gameToFind = this.gameRepository.findById(id).orElse(null);
        if (gameToFind == null) {
            return sb.append("Game doesn't exist").toString();
        }

        gameToFind.setPrice(editGameDto.getPrice());
        this.gameRepository.saveAndFlush(gameToFind);
        sb.append(String.format("Edited %s", gameToFind.getTitle()));

        return sb.toString();
    }

    @Override
    public String deleteGame(DeleteGameDto deleteGameDto) {

        checkRole();

        StringBuilder sb = new StringBuilder();
        Game game = this.gameRepository.findById(deleteGameDto.getId()).orElse(null);

        if (game == null) {
            return sb.append("Game does not exist!").toString();
        }

//        this.gameRepository.deleteGameById(game.getId());
        this.gameRepository.delete(game);

        sb.append(String.format("Deleted %s", game.getTitle()));
        return sb.toString();
    }
}
