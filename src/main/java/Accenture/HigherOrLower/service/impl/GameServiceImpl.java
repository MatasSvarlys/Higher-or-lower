package Accenture.HigherOrLower.service.impl;

import Accenture.HigherOrLower.model.Game;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl {

    Game game;
    public void createGame() {

        game = new Game(1);
    }

    public void incrementCurrentScore(){

        game.setCurrentScore(game.getCurrentScore()+1);
    }

    public int getCurrentScore(){

        return game.getCurrentScore();
    }

}
