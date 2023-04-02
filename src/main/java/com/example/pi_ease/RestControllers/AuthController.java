package com.example.pi_ease.RestControllers;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
@RestController
@AllArgsConstructor
public class AuthController {



    UserRepository userRepositories;
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepositories.findByUsername(principal.getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getSubject()));
    }



}
