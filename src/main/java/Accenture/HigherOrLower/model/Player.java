package Accenture.HigherOrLower.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "players", schema = "public")
public class Player implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="age")
    private int age;
    @Column(name="name")
    private String name;
    @Column(name="highscore")
    private int highscore;
    @Column(name="password")
    private String password;
    @Transient
    private List<Integer> usedIdsInGame;
    @Transient
    private int currentScore;

    public Player() {
    }

    public void resetCurrentScore() {
        this.currentScore = -1;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public Player(int id, int age, String name){
        this.id = id;
        this.age = age;
        this.name = name;
    }
    public int getId() {
        return id;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
