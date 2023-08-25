package Accenture.HigherOrLower.service.impl;

import Accenture.HigherOrLower.model.Celebrity;
import Accenture.HigherOrLower.model.Game;
import Accenture.HigherOrLower.repository.CelebrityRepository;
import Accenture.HigherOrLower.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl {

    @Autowired
    CelebrityRepository celebrityRepository;

    @Autowired
    PlayerRepository playerRepository;

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

    public List<Celebrity> getAllLT(){
        List<Celebrity> CelebritiesLT;
        CelebritiesLT = celebrityRepository.findCelebrityByCountry("LT");

        return CelebritiesLT;
    }
    public List<Celebrity> getAllLV(){
        List<Celebrity> CelebritiesLV;
        CelebritiesLV = celebrityRepository.findCelebrityByCountry("LV");

        return CelebritiesLV;
    }

    public void addLVIdToExclude(int cid)
    {
        int id = (cid - 1)/2;
        game.addLVIdToList(id);

    }

    public void addLTIdToExclude(int cid)
    {
        int id = cid/2;
        game.addLTIdToList(id);

    }

    public Celebrity getRandomLTCelebrity()
    {
        Random random = new Random();
        int randomId;

        do {
            randomId = random.nextInt(48);
        } while (game.getUsedIdsLT().contains(randomId));

        return celebrityRepository.findCelebrityById(randomId*2);
    };
    public Celebrity getRandomLVCelebrity()
    {
        Random random = new Random();
        int randomId;

        do {
            randomId = random.nextInt(48);
        } while (game.getUsedIdsLT().contains(randomId));

        return celebrityRepository.findCelebrityById((randomId*2)+1);
    };

    public List<Integer> getUsedIds()
    {
        return game.getUsedIdsLT();
    };



}
