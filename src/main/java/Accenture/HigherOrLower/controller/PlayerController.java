package Accenture.HigherOrLower.controller;

import Accenture.HigherOrLower.model.Celebrity;
import Accenture.HigherOrLower.model.Player;
import Accenture.HigherOrLower.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/")
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/")
    public String showTopScores(Model model) {
        List<Player> topScores = playerRepository.getTop5Players();
        model.addAttribute("Score", topScores);
        return "index";
    }

}
