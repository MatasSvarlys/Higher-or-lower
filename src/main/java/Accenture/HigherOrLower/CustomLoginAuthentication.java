package Accenture.HigherOrLower;

import Accenture.HigherOrLower.model.Player;
import Accenture.HigherOrLower.repository.PlayerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;

public class CustomLoginAuthentication extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    @Autowired
    private PlayerRepository playerRepository;

    public CustomLoginAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        System.out.println("It doesnt even excecute does it...");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("It doesnt even excecute does it...");

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Player player = playerRepository.findByName(username);

            if (player != null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (passwordEncoder.matches(password, player.getPassword())) {
                    // Password matches, so the user is authenticated

                    // Create a successful authentication token
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            username, password, new ArrayList<>());
                    return token;
                }
            }

            // Authentication failed, throw exception
            throw new BadCredentialsException("Invalid credentials");

        } catch (Exception e) {
//            log.error("Authentication Error {}", e.getMessage());
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }


}
