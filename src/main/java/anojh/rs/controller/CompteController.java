package anojh.rs.controller;

import anojh.rs.modele.Compte;
import anojh.rs.service.CompteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compte")
@AllArgsConstructor
public class CompteController {

    private final CompteService compteService;

    @PostMapping("/creer")
    public ResponseEntity<Compte> creer(@RequestBody Compte compte) {
        Compte nouveau = compteService.creer(compte);
        return ResponseEntity.status(201).body(nouveau);
    }

    @GetMapping("/read")
    public List<Compte> getAllCompte() {
        return compteService.getAllComptes();
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Compte> getCompte(@PathVariable Long id){
        return compteService.getCompte(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Compte> setCompte(@PathVariable Long id, @RequestBody Compte comptemodifie) {
        Compte modifie = compteService.setCompte(id, comptemodifie);
        return ResponseEntity.ok(modifie);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        compteService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}
