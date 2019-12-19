package tenev.gamestore.services;

import tenev.gamestore.domain.dtos.AddGameDto;
import tenev.gamestore.domain.dtos.DeleteGameDto;
import tenev.gamestore.domain.dtos.EditGameDto;

public interface GameService {

    String addGame(AddGameDto addGameDto);

    String editGame(EditGameDto editGameDto);

    String deleteGame(DeleteGameDto deleteGameDto);
}
