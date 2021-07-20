package net.reifenapp.reifenmodelle.model;

import java.util.List;

// Meant to be a return Statement in UserController
public class UserRoles {
    private String username;
    private List<String> roles;

    public UserRoles(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
