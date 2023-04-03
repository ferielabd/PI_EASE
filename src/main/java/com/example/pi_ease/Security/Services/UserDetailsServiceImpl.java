package com.example.pi_ease.Security.Services;

import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Builder
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepositories;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        User user=userRepositories.findByUsername(Username)
                .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND WITH USERNAME: "+ Username));
        return  UserDetailslmpl.build(user);
    }
}

