package net.reifenapp.reifenmodelle.resource;

import net.reifenapp.reifenmodelle.model.User;
import net.reifenapp.reifenmodelle.model.UserRoles;
import net.reifenapp.reifenmodelle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<UserRoles> loginUser(@RequestBody User user) {
        // Better with
        //  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userTmp = userService.findByUsername(user.getUsername());
        if( user.getUsername().matches(userTmp.getUsername()) &&
                bCryptPasswordEncoder.matches(user.getPassword(), userTmp.getPassword())) {
            return new ResponseEntity<>( new UserRoles(userTmp.getUsername(),
                    userTmp.getRoles().stream().map((role) -> role.getName()).collect(Collectors.toList()))
            ,HttpStatus.OK);
        }
        return null;
    }
}
