package Accenture.HigherOrLower.controller;

import Accenture.HigherOrLower.model.Celebrity;
import Accenture.HigherOrLower.repository.CelebrityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/")
public class CelebrityController {

    @Autowired
    CelebrityRepository celebrityRepository;
    private final int maxLt = 44;
    private final int maxLv = 47;
    private int amountOfUnusedLt = maxLt;
    private int amountOfUnusedLv = maxLv;
    private boolean[] usedLtId = new boolean[maxLt];
    private boolean[] usedLvId = new boolean[maxLv];
    /*@Autowired
    public CelebrityController(CelebrityRepository celebrityRepository) {
        this.celebrityRepository = celebrityRepository;
    }*/

    @GetMapping("/celebrities")
    public List<Celebrity> getListOfCelebritiesByCountry(@RequestParam String countryCode) {
        return celebrityRepository.findByCountryIgnoreCase(countryCode);
    }
    @GetMapping("/celebrities/searchCount")
    public int getGoogleSearchCountByName(@RequestParam String name) {
        return celebrityRepository.findGoogleSearchCountByName(name);
    }

    @GetMapping("gameLogic/getRandomCelebrity")
    public Celebrity getRandomCelebrityByCountry(@RequestParam String countryCode){
        List<Celebrity> c;
        c = celebrityRepository.findByCountryIgnoreCase(countryCode);
        if("LT".equalsIgnoreCase(countryCode)){
            int randomIndex = new Random().nextInt(amountOfUnusedLt);
            int i = 0;
            while(i < randomIndex){
                if(usedLtId[i])
                    randomIndex++;

                if(randomIndex > maxLt)
                    return null; //should throw an error no more lt celebrities left
                i++;
            }
            usedLtId[i] = true;
            amountOfUnusedLt--;
            return c.get(i);
        } else {
            int randomIndex = new Random().nextInt(amountOfUnusedLv);
            int i = 0;
            while (i < randomIndex) {
                if (usedLvId[i])
                    randomIndex++;

                if (randomIndex >= maxLv)
                    return null; // No more LV celebrities left, return an error
                i++;
            }
            usedLvId[i] = true;
            amountOfUnusedLv--;
            return c.get(i);
        }
    }

    @GetMapping("gameLogic/compareCelebrities")
    public String compareTwoCelebrities(Model model){
        Random rand = new Random();
        int rand_int1 = rand.nextInt(92);
        int rand_int2 = rand.nextInt(92);
        int searchCount1 = celebrityRepository.findCelebrityById(rand_int1).getGoogleSearchCount();
        int searchCount2 = celebrityRepository.findCelebrityById(rand_int2).getGoogleSearchCount();

        if(searchCount1 > searchCount2)
            model.addAttribute("celebrity", celebrityRepository.findCelebrityById(rand_int1));
        else
            model.addAttribute("celebrity", celebrityRepository.findCelebrityById(rand_int2));

        return "compare";
    }
    @GetMapping("/test")
    public String getTest(Model model){
        model.addAttribute("celebrity", celebrityRepository.findAll());
        return "test";
    }
    //Celebrity c = celebrityRepository.findCelebrityByID(1);
    @GetMapping("/maybe")
    public String getMaybe(Model model){
        Random rand = new Random();
        int rand_int1 = rand.nextInt(92);
        int rand_int2 = rand.nextInt(92);
        model.addAttribute("celebrity", celebrityRepository.findCelebrityById(rand_int1));
        model.addAttribute("test", celebrityRepository.findCelebrityById(rand_int2));
        return "maybe";
    }
    @GetMapping("/choice")
    public String getChoice(Model model){
        Random rand = new Random();
        int rand_int1 = rand.nextInt(92);
        int rand_int2 = rand.nextInt(92);
        model.addAttribute("celebrity", celebrityRepository.findCelebrityById(rand_int1));
        model.addAttribute("test", celebrityRepository.findCelebrityById(rand_int2));

        int x = 10;
        return "choicepage";
    }


}
