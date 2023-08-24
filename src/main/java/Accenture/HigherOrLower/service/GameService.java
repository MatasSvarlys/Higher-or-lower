package Accenture.HigherOrLower.service;

import org.springframework.stereotype.Service;


public interface GameService {

    public void createGame();
    public void incrementCurrentScore();
    public int getCurrentScore();
}
