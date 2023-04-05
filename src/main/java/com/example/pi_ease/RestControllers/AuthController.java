package com.example.pi_ease.RestControllers;
<<<<<<< HEAD

import com.example.pi_ease.DAO.Entities.Role;
import com.example.pi_ease.DAO.Entities.TypeRole;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.RoleRespository;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.payload.Request.LoginRequest;
import com.example.pi_ease.payload.Request.SignupRequest;
import com.example.pi_ease.payload.Response.JwtResponse;
import com.example.pi_ease.payload.Response.MessageResponse;
import com.example.pi_ease.Security.JWT.JwtUtils;
import com.example.pi_ease.Security.Services.UserDetailslmpl;

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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
=======
import com.example.pi_ease.DAO.Entities.Role;
import com.example.pi_ease.DAO.Entities.TypeRole;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.RoleRepository;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.payload.request.LoginRequest;
import com.example.pi_ease.payload.request.SignupRequest;
import com.example.pi_ease.payload.response.JwtResponse;
import com.example.pi_ease.payload.response.MessageResponse;
import com.example.pi_ease.security.jwt.JwtUtils;
import com.example.pi_ease.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
<<<<<<< HEAD
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@Repository
@AllArgsConstructor
public class AuthController  {

    @Autowired
    AuthenticationManager authenticationManager;


    UserRepository userRepositories ;

    RoleRespository roleRepositories;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
=======
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    UserRepository userRepositories;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired(required = true)
    private JavaMailSender javaMailSender;
>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
<<<<<<< HEAD
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
=======

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup/{email}")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, @PathVariable("email") String email ) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

<<<<<<< HEAD
        if (userRepositories.existsByMail(signUpRequest.getMail())) {
=======
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
<<<<<<< HEAD
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
=======

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
        );


        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(TypeRole.ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(TypeRole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "AGENT_SALES":
                        Role modRole = roleRepository.findByName(TypeRole.AGENT_SALES)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    case "CLIENT":
                        Role client = roleRepository.findByName(TypeRole.CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(client);
                    case "INVESTORP":
                        Role INVESTORP = roleRepository.findByName(TypeRole.INVESTOR_P)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(INVESTORP);
                        break;
                    case "INVESTORNP":
                        Role INVESTORNP = roleRepository.findByName(TypeRole.INVESTOR_NP)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(INVESTORNP);
                        break;
                    default:
                        Role ADMIN = roleRepository.findByName(TypeRole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(ADMIN);
>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b
                }
            });
        }

        user.setRoles(roles);
<<<<<<< HEAD
        userRepositories.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
=======
        userRepository.save(user);

        sendEmail(signUpRequest.getEmail(), true);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }



    @GetMapping("/sendverifier/{email}")
    @ResponseBody
    public Boolean sendverifierUserAcount(
            @PathVariable ("email") String email) {

        sendEmail(email, true);
        return userRepository.existsByEmail(email);


    }
    @GetMapping("verifier/{id}")
    @ResponseBody
    public User verifierUserAcount(@PathVariable ("id") Long id) {

        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid User Id:" + id));
        user.setVerifier(1);
        return userRepository.save(user);
>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b


    }


<<<<<<< HEAD

}

=======
    void sendEmail(String email, boolean state) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        System.out.println(email);
        if(state == true)
        {

            msg.setSubject("Vérification d'email");
            msg.setText("Bonjour, Veuillez cliquer sur ce lien pour confirmer votre adresse email et vérifier votre compte. "
                    +
                    "You can log in : http://localhost:4200/#/login"
                    + " \n Best Regards!");
        }
        else
        {
            msg.setTo(email);
            msg.setSubject("s'il vous plaît verifier votre mail");
            msg.setText("Hello,votre email est incorrect. veuillez le vérifier.");
        }
        javaMailSender.send(msg);

    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepositories.findByUsername(principal.getSubject()).orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getSubject()));
    }



}
>>>>>>> 1d607ee2204a46c9189a5d07d71b3c328a205d9b
