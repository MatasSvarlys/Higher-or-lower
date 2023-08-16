package Accenture.HigherOrLower;

import java.util.ArrayList;
import java.util.List;

import Accenture.HigherOrLower.repository.CelebrityRepository;
import org.springframework.stereotype.Component;

@Component
public class CelebrityDatabase {

    private final CelebrityRepository celebrityRepository;

    public CelebrityDatabase(CelebrityRepository celebrityRepository) {
        this.celebrityRepository = celebrityRepository;

        celebrityRepository.save(new Celebrity("Jonas Valanciunas", "Lithuania", 1000000));
        celebrityRepository.save(new Celebrity("Kristaps Porzingis", "Latvia", 900000));
        celebrityRepository.save(new Celebrity("Rolands Smits", "Latvia", 900000));
    }

    public List<Celebrity> getListOfCelebritiesByCountry(String countryCode) {
        return celebrityRepository.findByCountryIgnoreCase(countryCode);
    }
}

