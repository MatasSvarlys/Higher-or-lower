package Accenture.HigherOrLower.controller;

import Accenture.HigherOrLower.model.Player;
import Accenture.HigherOrLower.repository.PlayerRepository;
import Accenture.HigherOrLower.service.GameService;
import Accenture.HigherOrLower.service.impl.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    private GameServiceImpl gameServiceImpl;

    private Player loggedInPlayer;

    private boolean loggedIn;


    @GetMapping("/")
    public String showTopScores(Model model) {
        List<Player> topScores = playerRepository.getTop5Players();
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("Score", topScores);
        if (loggedInPlayer != null) {
            model.addAttribute("Id", loggedInPlayer.getId());
        }
        else {
            model.addAttribute("Id", (-1));
        }
        gameServiceImpl.createGame();

        return "index";
    }

    @GetMapping("/login")
    public String loginValidation(Model model){
        return "login";
    }

    @GetMapping("/signup")
    public String signupValidation(Model model){

        return "signup";
    }
    @PostMapping("/signup")
    public String signUp(@RequestParam String name, @RequestParam String password, @RequestParam int age) {
        Player newUser = new Player();
        newUser.setName(name);
        newUser.setPassword(password);
        newUser.setAge(age);

        // Save the user to the database
        playerRepository.save(newUser);

        // Redirect to a success page or login page
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        Player player = playerRepository.findByNameAndPassword(name, password);

        if (player != null) {
            loggedIn = true;
            loggedInPlayer = player;
            return "redirect:/";
        } else {
            return "redirect:/login?error=UserNotFound";
        }
    }



    @GetMapping("/logout")
    public String logOut() {
        // Reset the loggedIn state
        loggedIn = false;
        loggedInPlayer = null;

        // Redirect to the login page or home page
        return "redirect:/"; // or another appropriate URL
    }

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
