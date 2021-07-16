package net.reifenapp.reifenmodelle.service;

import net.reifenapp.reifenmodelle.model.Reifenmodell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.reifenapp.reifenmodelle.repository.ReifenmodellRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ReifenmodellService {
    private final ReifenmodellRepo reifenmodellRepo;

    @Autowired
    public ReifenmodellService(ReifenmodellRepo reifenmodellRepo) {
        this.reifenmodellRepo = reifenmodellRepo;
    }

    public Reifenmodell addReifenmodell(Reifenmodell reifenmodell) {
        return reifenmodellRepo.save(reifenmodell);
    }

    public List<Reifenmodell> findAllReifenmodell() {
        return reifenmodellRepo.findAll();
    }

    public Reifenmodell updateReifenmodell(Reifenmodell reifenmodell) {
        return reifenmodellRepo.save(reifenmodell);
    }

    public void deleteReifenmodell(Long id) {
        reifenmodellRepo.deleteReifenmodellById(id);
    }

    public Reifenmodell findReifenmodellById(Long id) {
        return reifenmodellRepo.findReifenmodellById(id)
                .orElseThrow(() -> new NoSuchElementException("Reifenmodell mit ID " + id + " nicht gefunden."));
    }
}

