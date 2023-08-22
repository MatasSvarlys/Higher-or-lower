package Accenture.HigherOrLower.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "players", schema = "public")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="age")
    private int age;
    @Column(name="name")
    private String name;
    @Column(name="highscore")
    private int highscore;
    @Transient
    private List<Integer> usedIdsInGame;

    public Player() {
    }

    public Player(int id, int age, String name){
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public void putIdIntoUsed(int celebrityId){
        usedIdsInGame.add(celebrityId);
    }
    public void clearIdsFromUsed(){
        usedIdsInGame.clear();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
