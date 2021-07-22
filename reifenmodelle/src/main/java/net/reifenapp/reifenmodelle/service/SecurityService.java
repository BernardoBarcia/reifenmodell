package net.reifenapp.reifenmodelle.service;

import net.reifenapp.reifenmodelle.model.User;
import net.reifenapp.reifenmodelle.model.UserRoles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SecurityService {

    public boolean checkAdminAuthorization() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("admin"));
    }
    public UserRoles getUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) return null;
        return new UserRoles(authentication.getName(),
                authentication.getAuthorities().stream().map((role) -> role.getAuthority()).collect(Collectors.toList()));
    }
}
