package com.accenture.higherorlower.model;
import jakarta.persistence.*;

@Entity
@Table(name = "celebrities", schema = "public")
public class Celebrity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="country")
    private String country;
    @Column(name="googlesearchcount")
    private int googleSearchCount;

    public Celebrity() {
    }

    public Celebrity(String name, String country, int googleSearchCount) {
        this.name = name;
        this.country = country;
        this.googleSearchCount = googleSearchCount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getGoogleSearchCount() {
        return googleSearchCount;
    }

    public void setGoogleSearchCount(int googleSearchCount) {
        this.googleSearchCount = googleSearchCount;
    }
}

