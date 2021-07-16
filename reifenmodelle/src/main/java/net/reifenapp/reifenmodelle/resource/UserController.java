package net.reifenapp.reifenmodelle.resource;

import net.reifenapp.reifenmodelle.model.Reifenmodell;
import net.reifenapp.reifenmodelle.model.User;
import net.reifenapp.reifenmodelle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public boolean loginUser(@RequestBody User user) {
        User userTmp = userService.findByUsername(user.getUsername());
        return user.getUsername().matches(userTmp.getUsername()) && bCryptPasswordEncoder.matches(user.getPassword(), userTmp.getPassword());

    }
}
