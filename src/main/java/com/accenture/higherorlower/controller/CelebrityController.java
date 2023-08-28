package com.accenture.higherorlower.controller;

import com.accenture.higherorlower.model.Celebrity;
import com.accenture.higherorlower.repository.CelebrityRepository;
import com.accenture.higherorlower.repository.PlayerRepository;
import com.accenture.higherorlower.service.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/")
public class CelebrityController {

    @Autowired
    CelebrityRepository celebrityRepository;
    private final int maxLt = 48;
    private final int maxLv = 48;
    private int amountOfUnusedLt = maxLt;
    private int amountOfUnusedLv = maxLv;
    private boolean[] usedLtId = new boolean[maxLt];
    private boolean[] usedLvId = new boolean[maxLv];
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameServiceImpl gameServiceImpl;

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

    @GetMapping("/game-board/{pid}/{cid}")
    public String getGameBoard(Model model,@PathVariable(name="pid") int pid, @PathVariable(name="cid") int cid) {
        Random rand = new Random();
        if(cid <= 0){
            cid = (int) (Math.floor(Math.random() * 96) + 1);
        }

        Boolean correctAnswer = false;

        if (!Objects.equals(celebrityRepository.findCelebrityById(cid).getCountry(), "LT")) {
            gameServiceImpl.addLVIdToExclude(cid);
        } else {
            gameServiceImpl.addLTIdToExclude(cid);
        }

        model.addAttribute("score", gameServiceImpl.getCurrentScore());

        gameServiceImpl.incrementCurrentScore();

        model.addAttribute("playerId", pid);
        model.addAttribute("Id", pid);

        Celebrity celebrityLeft = celebrityRepository.findCelebrityById(cid);
        model.addAttribute("celebrityLeft", celebrityLeft);

        List<Celebrity> usedCelebrities = new ArrayList<>(); // List to track used celebrities
        usedCelebrities.add(celebrityLeft); // Add celebrityLeft to the usedCelebrities list

        // Fetch candidates based on the country of celebrityLeft
        List<Celebrity> candidates;
        if (!Objects.equals(celebrityLeft.getCountry(), "LT")) {
            candidates = gameServiceImpl.getAllLT();
        } else {
            candidates = gameServiceImpl.getAllLV();
        }

        // Remove used celebrities from candidates list
        candidates.removeAll(usedCelebrities);

        if (candidates.isEmpty()) {
            // All available candidates are used, handle accordingly
        } else {
            int rand_intRight = rand.nextInt(candidates.size());
            Celebrity celebrityRight = candidates.get(rand_intRight);

            if (!Objects.equals(celebrityLeft.getCountry(), "LT"))
            {
                model.addAttribute("celebrityRight", gameServiceImpl.getRandomLVCelebrity());
                model.addAttribute("celebrityRightID", gameServiceImpl.getRandomLVCelebrity());
            } else
            {
                model.addAttribute("celebrityRight", gameServiceImpl.getRandomLTCelebrity());
                model.addAttribute("celebrityRightID", gameServiceImpl.getRandomLTCelebrity());

            }

            model.addAttribute("celebrityRight", celebrityRight);
            model.addAttribute("celebrityRightID", celebrityRight.getId());

            if (celebrityRight.getGoogleSearchCount() >= celebrityLeft.getGoogleSearchCount()) {
                correctAnswer = true;
                model.addAttribute("answer", correctAnswer);
            } else {
                correctAnswer = false;
                model.addAttribute("answer", correctAnswer);
            }
        }

        return "game-board";
    }


}
