package Accenture.HigherOrLower.controller;

import Accenture.HigherOrLower.model.Player;
import Accenture.HigherOrLower.repository.PlayerRepository;
import ch.qos.logback.core.model.Model;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.remote.JMXAuthenticator;

@Controller
public class AuthController {
    @Autowired
    private PlayerRepository playerRepository;
    @GetMapping("/login")
    public String loginEndpoint(Model model){
        System.out.println("executed get code");
        return "login";
    }

    @GetMapping("/signup")
    public String signupValidation(Model model){
        return "signup";
    }

    @PostMapping("/login-custom")
    public String logIn(@RequestParam String username, @RequestParam String password) {
        Player player = playerRepository.findByName(username);
        System.out.println("executed post code 1");
        if (player != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            System.out.println("player found in the database");
            if (passwordEncoder.matches(password, player.getPassword())) {
                // Password matches, so the user is authenticated
                System.out.println("passwords matched");
                return "redirect:/"; // You can replace this with the appropriate success view or redirect
            }
        }
        System.out.println("executed post code 2");
        return "/login";
    }
    @PostMapping("/signup")
    public String signup(Player player) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(player.getPassword());
        player.setPassword(encodedPassword);

        playerRepository.save(player);

        return "register_success";
    }


}
