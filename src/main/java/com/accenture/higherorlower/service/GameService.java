package com.accenture.higherorlower.service;

import com.accenture.higherorlower.model.Celebrity;

import java.util.List;


public interface GameService {

    public void createGame();
    public void incrementCurrentScore();
    public int getCurrentScore();

    public List<Celebrity> getAllLT();
    public List<Celebrity> getAllLV();

    public void addLVIdToExclude(int cid);

    public void addLTIdToExclude(int cid);

    public Celebrity getRandomLTCelebrity();

    public Celebrity getRandomLVCelebrity();

    List<Integer> getUsedIds();
}
