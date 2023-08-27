package Accenture.HigherOrLower.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    int pid;
    int currentScore;

    List<Integer> usedIdsLT;

    List<Integer> usedIdsLV;

    public List<Integer> getUsedIdsLT() {
        return usedIdsLT;
    }

    public void addLTIdToList(int Id) {
        usedIdsLT.add(Id);
    }
    public List<Integer> getUsedIdsLV() {
        return usedIdsLV;
    }

    public void addLVIdToList(int Id) {
        usedIdsLV.add(Id);
    }



    public Game(int pid) {
        this.pid = pid;
        this.currentScore = 0;
        usedIdsLV = new ArrayList<>();
        usedIdsLT = new ArrayList<>();
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }



}
