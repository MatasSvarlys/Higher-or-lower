package Accenture.HigherOrLower.repository;

import Accenture.HigherOrLower.model.Celebrity;
import Accenture.HigherOrLower.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p ORDER BY p.highscore DESC LIMIT 5")
    List<Player> getTop5Players();

    @Query(value = "SELECT * FROM players OFFSET floor(random() * (SELECT COUNT(*) FROM players)) LIMIT 1", nativeQuery = true)
    Player getRandomPlayer();


}
