package Accenture.HigherOrLower.controller;

import Accenture.HigherOrLower.model.Celebrity;
import Accenture.HigherOrLower.repository.CelebrityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/")
public class CelebrityController {

    @Autowired
    CelebrityRepository celebrityRepository;
    private final int maxLt = 46;
    private final int maxLv = 46;
    private int amountOfUnusedLt = maxLt;
    private int amountOfUnusedLv = maxLv;
    private boolean[] usedLtId = new boolean[maxLt];
    private boolean[] usedLvId = new boolean[maxLv];

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

    @GetMapping("/game-board/{id}")
    public String getGameBoard(Model model,  @PathVariable(name="id") int id){
        Random rand = new Random();
        Boolean correctAnswer = false;
        model.addAttribute("celebrityLeft", celebrityRepository.findCelebrityById(id));
        if(!Objects.equals(celebrityRepository.findCelebrityById(id).getCountry(), "LT")){
            List<Celebrity> celebrities = celebrityRepository.findCelebrityByCountry("LT");
            int rand_intLT = rand.nextInt(96);
            model.addAttribute("celebrityRight", celebrityRepository.findCelebrityById(rand_intLT));
            model.addAttribute("celebrityRightID", rand_intLT);
            if (celebrityRepository.findCelebrityById(rand_intLT).getGoogleSearchCount() >= celebrityRepository.findCelebrityById(id).getGoogleSearchCount()) {
                correctAnswer = true;
                model.addAttribute("answer", correctAnswer);
            }
            else {
                correctAnswer = false;
            }


        }
        else{

            List<Celebrity> celebrities = celebrityRepository.findCelebrityByCountry("LV");
            int rand_intLV = rand.nextInt(96);
            model.addAttribute("celebrityRight", celebrityRepository.findCelebrityById(rand_intLV));
            model.addAttribute("celebrityRightID", rand_intLV);
            if (celebrityRepository.findCelebrityById(rand_intLV).getGoogleSearchCount() >= celebrityRepository.findCelebrityById(id).getGoogleSearchCount()) {
                correctAnswer = true;
                model.addAttribute("answer", correctAnswer);
            }
            else {
                correctAnswer = false;
            }
        }


        return "game-board";
    }

}
