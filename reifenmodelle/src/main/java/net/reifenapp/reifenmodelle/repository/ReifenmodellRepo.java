package net.reifenapp.reifenmodelle.repository;

import net.reifenapp.reifenmodelle.model.Reifenmodell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReifenmodellRepo extends JpaRepository<Reifenmodell, Long> {
    // Spring creates query
    void deleteReifenmodellById(Long id);

    Optional<Reifenmodell> findReifenmodellById(Long id);
}
