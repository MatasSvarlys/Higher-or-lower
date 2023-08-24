package Accenture.HigherOrLower.model;

import java.util.List;

public class Game {

    int pid;
    int currentScore;

    List usedIds;

    public Game(int pid) {
        this.pid = pid;
        this.currentScore = 0;
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
