package com.accenture.higherorlower.controller;

import com.accenture.higherorlower.model.Player;
import com.accenture.higherorlower.repository.PlayerRepository;
import com.accenture.higherorlower.service.impl.GameServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    private GameServiceImpl gameServiceImpl;

    private Player loggedInPlayer;


    @GetMapping("/")
    public String showTopScores(Model model, Authentication authentication, HttpSession session) {
        List<Player> topScores = playerRepository.getTop5Players();
        model.addAttribute("Score", topScores);
        //Make the authenticator go brr
        if (authentication != null && authentication.isAuthenticated()) {
            // Player loggedInPlayer = (Player) authentication.getPrincipal();
            authentication.getName();
            authentication.getDetails();
            model.addAttribute("loggedIn", true);
            model.getAttribute("Id");
           // model.addAttribute("Id", loggedInPlayer.getId());
        } else {
            model.addAttribute("loggedIn", false);
            model.addAttribute("Id", -1);
            //session.setAttribute("username", null);
        }
        gameServiceImpl.createGame();

        return "index";
    }

    //    @GetMapping("/{pid}")
//    public String getHomePage(Model model,@PathVariable(name="pid") int pid) {
//        List<Player> topScores = playerRepository.getTop5Players();
//        model.addAttribute("loggedIn", loggedIn);
//        model.addAttribute("Score", topScores);
//        model.addAttribute("Id", pid);
//
//        gameServiceImpl.createGame();
//
//        return "index";
//    }

//    @GetMapping("/logout")
//    public String logOut() {
//        // Reset the loggedIn state
//        loggedInPlayer = null;
//
//        // Redirect to the login page or home page
//        return "redirect:/"; // or another appropriate URL
//    }

    @GetMapping("/end/{pid}")
    public String getEnd(Model model, @PathVariable(name = "pid") int pid) {
        Player player = playerRepository.findById(pid);
        player.setCurrentScore(gameServiceImpl.getCurrentScore() - 1);

        if (gameServiceImpl.getCurrentScore() > player.getHighscore()) {
            player.setHighscore(player.getCurrentScore());

            // Save the updated player object back to the database
            playerRepository.save(player);
        }

        model.addAttribute("player", player);
        model.addAttribute("answer", false);
        return "end";
    }
}
