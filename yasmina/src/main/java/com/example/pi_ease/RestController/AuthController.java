package com.example.pi_ease.RestControllers;

import com.example.pi_ease.DAO.Entities.Role;
import com.example.pi_ease.DAO.Entities.TypeRole;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.RoleRespository;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.DAO.Request.LoginRequest;
import com.example.pi_ease.DAO.Request.SignupRequest;
import com.example.pi_ease.DAO.Response.JwtResponse;
import com.example.pi_ease.DAO.Response.MessageResponse;
import com.example.pi_ease.DAO.Security.JWT.JwtUtilsateur;
import com.example.pi_ease.DAO.Security.Services.UserDetailslmpl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RestController
@Repository
@AllArgsConstructor
public class AuthController  {

    @Autowired
    AuthenticationManager authenticationManager;


    UserRepository userRepositories;

    RoleRespository roleRepositories;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtilsateur jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        boolean active = userRepositories.findByUsername(loginRequest.getUsername()).get().isActive();
        if (active) {
            System.err.println("pwd.22..." + loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            System.out.println("pwd...." + loginRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailslmpl userDetails = (UserDetailslmpl) authentication.getPrincipal();
            User u = userRepositories.findByUsername(userDetails.getUsername()).get();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());


            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getMail(),
                    userDetails.getPhone(),
                    userDetails.getBirthDate(),
                    roles));
        } else {
            return ResponseEntity.ok("user non active");
        }
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepositories.findByUsername(principal.getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getSubject()));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepositories.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepositories.existsByMail(signUpRequest.getMail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
//User user=new User();
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getMail(), signUpRequest.getBirthDate(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhone());

        Set<String> strRoles = signUpRequest.getRoles();
        List<Role> roles = new ArrayList<>();
       /* Role ClientRole = roleRepositories.findByTypeRole(TypeRole.CLIENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(ClientRole);*/
        if (strRoles == null) {
            Role AdminRole = roleRepositories.findByTypeRole(TypeRole.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(AdminRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "CLIENT":
                        Role ClientRole = roleRepositories.findByTypeRole(TypeRole.CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(ClientRole);

                        break;
                    case "INVESTOR":
                        Role InvestorRole = roleRepositories.findByTypeRole(TypeRole.INVESTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(InvestorRole);

                        break;
                    case "AGENT_SALES":
                        Role AGENT_Sales = roleRepositories.findByTypeRole(TypeRole.AGENT_SALES)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(AGENT_Sales);

                        break;
                    case "INVESTORP":
                        Role INVESTORP = roleRepositories.findByTypeRole(TypeRole.INVESTORP)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(INVESTORP);

                        break;
                    case "INVESTORNP":
                        Role INVESTORNP = roleRepositories.findByTypeRole(TypeRole.INVESTORNP)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(INVESTORNP);

                        break;
                    default:
                        Role userRole = roleRepositories.findByTypeRole(TypeRole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepositories.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));


    }



}

