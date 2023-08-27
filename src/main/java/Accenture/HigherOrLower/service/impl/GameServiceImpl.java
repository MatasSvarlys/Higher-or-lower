package Accenture.HigherOrLower.service.impl;

import Accenture.HigherOrLower.model.Celebrity;
import Accenture.HigherOrLower.model.Game;
import Accenture.HigherOrLower.repository.CelebrityRepository;
import Accenture.HigherOrLower.repository.PlayerRepository;
import Accenture.HigherOrLower.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    private final CelebrityRepository celebrityRepository;
    private final PlayerRepository playerRepository;
    private Game game;

    @Autowired
    public GameServiceImpl(CelebrityRepository celebrityRepository, PlayerRepository playerRepository) {
        this.celebrityRepository = celebrityRepository;
        this.playerRepository = playerRepository;
    }
    public void setGame(Game game) {
        this.game = game;
    }

    // Getter method for game
    public Game getGame() {
        return game;
    }

    @Override
    public void createGame() {
        game = new Game(1);
    }

    @Override
    public void incrementCurrentScore() {
        if (game != null) {
            game.setCurrentScore(game.getCurrentScore() + 1);
        }
    }

    @Override
    public int getCurrentScore() {
        return game != null ? game.getCurrentScore() : 0;
    }

    @Override
    public List<Celebrity> getAllLT() {
        return celebrityRepository.findCelebrityByCountry("LT");
    }

    @Override
    public List<Celebrity> getAllLV() {
        return celebrityRepository.findCelebrityByCountry("LV");
    }

    @Override
    public void addLVIdToExclude(int cid) {
        if (game != null) {
            int id = (cid - 1) / 2;
            game.addLVIdToList(id);
        }
    }

    @Override
    public void addLTIdToExclude(int cid) {
        if (game != null) {
            int id = cid / 2;
            game.addLTIdToList(id);
        }
    }

    @Override
    public Celebrity getRandomLTCelebrity() {
        if (game != null) {
            Random random = new Random();
            int randomId;

            do {
                randomId = random.nextInt(48);
            } while (game.getUsedIdsLT().contains(randomId));

            return celebrityRepository.findCelebrityById(randomId * 2);
        }
        return null;
    }

    @Override
    public Celebrity getRandomLVCelebrity() {
        if (game != null) {
            Random random = new Random();
            int randomId;

            do {
                randomId = random.nextInt(48);
            } while (game.getUsedIdsLV().contains(randomId));

            return celebrityRepository.findCelebrityById((randomId * 2) + 1);
        }
        return null;
    }

    @Override
    public List<Integer> getUsedIds() {
        return game != null ? game.getUsedIdsLT() : null;
    }


}
