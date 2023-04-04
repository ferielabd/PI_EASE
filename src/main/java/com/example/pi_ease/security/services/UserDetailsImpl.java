package com.example.pi_ease.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Autowired
    private UserRepository userRepository;
	private Long id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
	 public List <User> getAll() {
	        return userRepository.findAll(Sort.by("login").ascending());
	    }

	 public Long save (User user) {
	    	User user1 = new User();
			user1.setUsername(user1.getUsername());
			user1.setPassword(user1.getPassword());
			user1.setEmail(user1.getEmail());
			user1.setFirstname(user1.getFirstname());
			user1.setLastname(user1.getLastname());
			user1.setBirthday(user1.getBirthday());
			user1.setCountry(user1.getCountry());
			user1.setZipcode(user1.getZipcode());
			user1.setRoles(user1.getRoles());
			user1.setNomImageidentity(user1.getNomImageidentity());
			user1.setNomImageselfie(user1.getNomImageselfie());



			return userRepository.save(user1).getId();

		
	    	
	    }
	    
	 public void delete (int id) {
	    	Optional <User> userEntity=userRepository.findById((long) id);
	    	userEntity.ifPresent(userRepository::delete);
	    }   
}
