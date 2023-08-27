package Accenture.HigherOrLower.controller;

import Accenture.HigherOrLower.model.Player;
import Accenture.HigherOrLower.repository.PlayerRepository;
import Accenture.HigherOrLower.service.GameService;
import Accenture.HigherOrLower.service.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public String showTopScores(Model model, Authentication authentication) {
        List<Player> topScores = playerRepository.getTop5Players();
        model.addAttribute("Score", topScores);
        //Make the authenticator go brr
        if (authentication != null && authentication.isAuthenticated()) {
            Player loggedInPlayer = (Player) authentication.getPrincipal();
            model.addAttribute("loggedIn", true);
            model.addAttribute("Id", loggedInPlayer.getId());
        } else {
            model.addAttribute("loggedIn", false);
            model.addAttribute("Id", -1);
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

//    @PostMapping("/signup")
//    public String signUp(@RequestParam String name, @RequestParam String password, @RequestParam int age) {
//        // Validate the input if needed
//        if (name == null || name.isEmpty() || password == null || password.isEmpty() || age < 7 || age == 0) {
//            return "signup";
//        }
//
//        // Create a new user entity
//        Player newUser = new Player();
//        newUser.setName(name);
//        newUser.setPassword(password);
//        newUser.setAge(age);
//
//        // Save the user to the database
//        playerRepository.save(newUser);
//
//        // Redirect to a success page or login page
//        return "redirect:/login";
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
        return "end";
    }
}
