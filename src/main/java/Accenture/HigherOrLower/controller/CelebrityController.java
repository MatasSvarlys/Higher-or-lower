package Accenture.HigherOrLower.controller;

import Accenture.HigherOrLower.model.Celebrity;
import Accenture.HigherOrLower.model.Player;
import Accenture.HigherOrLower.repository.CelebrityRepository;
import Accenture.HigherOrLower.repository.PlayerRepository;
import Accenture.HigherOrLower.service.GameService;
import Accenture.HigherOrLower.service.impl.GameServiceImpl;
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
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameServiceImpl gameServiceImpl;


    @GetMapping("/game-board/{pid}/{cid}")
    public String getGameBoard(Model model,@PathVariable(name="pid") int pid, @PathVariable(name="cid") int cid) {
        Random rand = new Random();
        Boolean correctAnswer = false;

        if (!Objects.equals(celebrityRepository.findCelebrityById(cid).getCountry(), "LT")) {
            gameServiceImpl.addLVIdToExclude(cid);
        } else {
            gameServiceImpl.addLTIdToExclude(cid);
        }

        model.addAttribute("score", gameServiceImpl.getCurrentScore());

        gameServiceImpl.incrementCurrentScore();

        model.addAttribute("playerId", pid);

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
            }
        }

        return "game-board";
    }


}
