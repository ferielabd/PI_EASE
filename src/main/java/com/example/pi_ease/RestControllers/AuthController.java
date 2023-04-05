package com.example.pi_ease.RestControllers;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.pi_ease.payload.request.LoginRequest;
import com.example.pi_ease.payload.request.SignupRequest;
import com.example.pi_ease.payload.response.JwtResponse;
import com.example.pi_ease.payload.response.MessageResponse;
import com.example.pi_ease.security.jwt.JwtUtils;
import com.example.pi_ease.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pi_ease.DAO.Entities.TypeRole;
import com.example.pi_ease.DAO.Entities.Role;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.RoleRepository;
import com.example.pi_ease.DAO.Repositories.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
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
	

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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


	@Transactional(readOnly = true)
	public User getCurrentUser() {
		Jwt principal = (Jwt) SecurityContextHolder.
				getContext().getAuthentication().getPrincipal();
		return userRepository.findByUsername(principal.getSubject())
				.orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getSubject()));
	}
	@PostMapping("/signup/{email}")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, @PathVariable ("email") String email ) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
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
				}
			});
		}

		user.setRoles(roles);
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
	   
  
    }
	
	
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
}
