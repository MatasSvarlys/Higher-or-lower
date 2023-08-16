package Accenture.HigherOrLower.controller;

import Accenture.HigherOrLower.Celebrity;
import Accenture.HigherOrLower.repository.CelebrityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CelebrityController {

    private final CelebrityRepository celebrityRepository;

    @Autowired
    public CelebrityController(CelebrityRepository celebrityRepository) {
        this.celebrityRepository = celebrityRepository;
    }

    @GetMapping("/celebrities")
    public List<Celebrity> getListOfCelebritiesByCountry(@RequestParam String countryCode) {
        return celebrityRepository.findByCountryIgnoreCase(countryCode);
    }
}
