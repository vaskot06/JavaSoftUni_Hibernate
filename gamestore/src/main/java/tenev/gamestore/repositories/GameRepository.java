package tenev.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tenev.gamestore.domain.entities.Game;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer> {

    Optional<Game> findByTitle(String title);

    Optional<Game> findById(Integer id);

    Optional<Game> deleteGameById(Integer id);

}
