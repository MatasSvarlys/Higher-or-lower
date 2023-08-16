package Accenture.HigherOrLower.controller;

import Accenture.HigherOrLower.Celebrity;
import Accenture.HigherOrLower.repository.CelebrityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ButtonController {

    private final CelebrityRepository celebrityRepository;
    @Autowired
    public ButtonController(CelebrityRepository celebrityRepository) {
        this.celebrityRepository = celebrityRepository;
    }

    @GetMapping("/lookupButtons")
    public int getPopularSearchScore(@RequestParam String name) {
        return celebrityRepository.findGoogleSearchCountByName(name);
    }

}
