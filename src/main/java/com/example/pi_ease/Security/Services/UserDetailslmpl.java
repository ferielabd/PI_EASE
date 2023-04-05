package com.example.pi_ease.Security.Services;


import com.example.pi_ease.DAO.Entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.stream.Collectors;


import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class UserDetailslmpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    long id;

    String username;


    int phone;

    Date birthDate;

    String mail;
    // String role;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailslmpl(long id, String username, String mail, String password, int phone, Date birthDate,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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


    public static UserDetailslmpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getTypeRole().name())).collect(Collectors.toList());

        return new UserDetailslmpl(
                 user.getId(),
                user.getUsername(),
                user.getMail(),
                user.getPassword(),
                user.getPhone(),
                user.getBirthDate(),
                authorities);
    }
    /*@Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailslmpl user = (UserDetailslmpl) o;
        return Object.equals(id, user.id);
        return true;
    }*/

    public int getPhone() {
        return phone;
    }
}

