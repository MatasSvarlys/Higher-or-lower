package Accenture.HigherOrLower;

import Accenture.HigherOrLower.model.Celebrity;
import Accenture.HigherOrLower.model.Game;
import Accenture.HigherOrLower.repository.CelebrityRepository;
import Accenture.HigherOrLower.repository.PlayerRepository;
import Accenture.HigherOrLower.service.impl.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
public class GameServiceImplTest {

    @Autowired
    private CelebrityRepository celebrityRepository;

    @Autowired
    private PlayerRepository playerRepository;

    private GameServiceImpl gameService;

    @BeforeEach
    public void setUp() {
        // Create a game instance with a dummy player ID
        Game game = new Game(1);

        // Instantiate the service using the created game instance
        gameService = new GameServiceImpl(celebrityRepository, playerRepository);
        gameService.setGame(game);

        // Optionally, you can set up any additional dependencies or configuration here
    }

    @Test
    public void testIncrementCurrentScore() {
        gameService.createGame();
        gameService.incrementCurrentScore();
        assertEquals(1, gameService.getCurrentScore());
    }

    @Test
    public void testGetCurrentScore() {
        int initialScore = gameService.getCurrentScore();
        assertEquals(0, initialScore); // Ensure initial score is 0

        gameService.incrementCurrentScore();
        int incrementedScore = gameService.getCurrentScore();
        assertEquals(1, incrementedScore); // Ensure score is incremented

        // You can add more test cases here to cover different scenarios
    }

    @Test
    public void testAddLTIdToExclude() {
        int initialSize = gameService.getGame().getUsedIdsLT().size();
        gameService.addLTIdToExclude(5); // For example, providing an id to exclude

        assertEquals(initialSize + 1, gameService.getGame().getUsedIdsLT().size());
    }

    @Test
    public void testAddLVIdToExclude() {
        int initialSize = gameService.getGame().getUsedIdsLV().size();
        gameService.addLVIdToExclude(6); // For example, providing an id to exclude

        assertEquals(initialSize + 1, gameService.getGame().getUsedIdsLV().size());
    }
    @Test
    public void testGetUsedIds() {
        // Set up the game with used IDs
        Game game = new Game(1);
        game.addLTIdToList(1);
        game.addLTIdToList(2);
        gameService.setGame(game);

        // Call the method you want to test
        assertEquals(2, gameService.getUsedIds().size());
    }

}
