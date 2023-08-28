package com.accenture.higherorlower.controller;

import com.accenture.higherorlower.model.Player;
import com.accenture.higherorlower.repository.PlayerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class AuthController {
    public static final String PLAYER = "player";
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
    public String logIn(@RequestParam String username, @RequestParam String password, Model model) {
        Player player = playerRepository.findByName(username);
        System.out.println("executed post code 1");
        if (player != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            System.out.println("player found in the database");
            if (passwordEncoder.matches(password, player.getPassword())) {
                // Password matches, so the user is authenticated
                System.out.println("passwords matched");
                model.addAttribute("loggedIn", true);
                model.addAttribute("Id", player.getId());
                return "index"; // You can replace this with the appropriate success view or redirect
            }
        }
        System.out.println("executed post code 2");
        return "login";
    }
    @PostMapping("/signup")
    public String signup(Player player, Model model, HttpSession session) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(player.getPassword());
        player.setPassword(encodedPassword);

        playerRepository.save(player);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(PLAYER);
        authorities.add(tempAuthority);
        org.springframework.security.core.userdetails.User user = new User(player.getName(), player.getPassword(), authorities);
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);

        session.setAttribute("user", user);

        return "index";
    }
}
