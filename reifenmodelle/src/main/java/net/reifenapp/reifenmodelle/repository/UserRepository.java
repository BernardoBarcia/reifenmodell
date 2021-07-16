package net.reifenapp.reifenmodelle.repository;

import net.reifenapp.reifenmodelle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
