package Accenture.HigherOrLower;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Celebrity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String country;
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

