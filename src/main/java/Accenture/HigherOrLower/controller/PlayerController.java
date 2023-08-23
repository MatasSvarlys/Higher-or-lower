package Accenture.HigherOrLower.controller;

import Accenture.HigherOrLower.model.Celebrity;
import Accenture.HigherOrLower.model.Player;
import Accenture.HigherOrLower.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/")
public class PlayerController {
    @Autowired
    PlayerRepository playerRepository;

    private boolean loggedIn;
    @GetMapping("/")
    public String showTopScores(Model model) {
        List<Player> topScores = playerRepository.getTop5Players();
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("Score", topScores);
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
        // Validate the input if needed
        if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
            return "signup";
        }

        if (age < 7) {
            return "signup";
        }
        // Create a new user entity
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
    public String logIn(@RequestParam String name, @RequestParam String password) {
        Player player = playerRepository.findByName(name);

        if (player == null) {
//            model.addAttribute("error", "Player not found");
            return "login"; // Return to the login page with an error message
        }

        if (!player.getPassword().equals(password)) {
            return "login"; // Return to the login page with an error message
        }

        loggedIn = true;
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logOut() {
        // Reset the loggedIn state
        loggedIn = false;

        // Redirect to the login page or home page
        return "redirect:/"; // or another appropriate URL
    }

    @GetMapping("/end/{pid}")
    public String getEnd(Model model,@PathVariable(name="pid") int pid) {
        model.addAttribute("player", playerRepository.findById(pid));
        playerRepository.findById(pid).resetCurrentScore();


        return "end";
    }
}
