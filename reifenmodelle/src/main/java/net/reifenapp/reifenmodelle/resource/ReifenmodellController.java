package net.reifenapp.reifenmodelle.resource;

import net.reifenapp.reifenmodelle.model.Reifenmodell;
import net.reifenapp.reifenmodelle.service.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import net.reifenapp.reifenmodelle.service.ReifenmodellService;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/reifenmodell")
public class ReifenmodellController {
    private final ReifenmodellService reifenmodellService;
    private final SecurityService securityService;

    public ReifenmodellController(ReifenmodellService ReifenmodellService, SecurityService securityService) {
        this.reifenmodellService = ReifenmodellService;
        this.securityService = securityService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reifenmodell>> getAllReifenmodelle() {
        List<Reifenmodell> reifenmodelle = reifenmodellService.findAllReifenmodell();
        return new ResponseEntity<>(reifenmodelle, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Reifenmodell> getReifenmodellById(@PathVariable("id") Long id) {
        Reifenmodell reifenmodell = reifenmodellService.findReifenmodellById(id);
        return new ResponseEntity<>(reifenmodell, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Reifenmodell> addReifenmodell(@RequestBody Reifenmodell Reifenmodell) {
        if(securityService.checkAdminAuthorization()) {
            Reifenmodell addReifenmodell = reifenmodellService.addReifenmodell(Reifenmodell);
            return new ResponseEntity<>(addReifenmodell, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/update")
    public ResponseEntity<Reifenmodell> updateReifenmodell(@RequestBody Reifenmodell Reifenmodell) {
        if(securityService.checkAdminAuthorization()) {
            Reifenmodell reifenmodell = reifenmodellService.updateReifenmodell(Reifenmodell);
            return new ResponseEntity<>(reifenmodell, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> updateReifenmodell(@PathVariable("id") Long id) {
        if(securityService.checkAdminAuthorization()) {
            reifenmodellService.deleteReifenmodell(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}