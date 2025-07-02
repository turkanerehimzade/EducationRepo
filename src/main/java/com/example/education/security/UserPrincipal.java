package com.example.education.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserPrincipal implements UserDetails {
    private Long id;
    private String email;
    private transient String password;
    private String roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null || roles.isEmpty()) {
            return Collections.emptyList();
        }

        String[] roleArray = roles.split(",");
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roleArray) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.trim()));
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
    public String toString() {
        return "UserPrincipal{" +
                "id=" + id +
                ", username='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
