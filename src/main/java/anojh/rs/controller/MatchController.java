package anojh.rs.controller;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Match;
import anojh.rs.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
@AllArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/read")
    public List<Match> getMatchs(@RequestBody Compte compte) {
        return matchService.getMatchs(compte);
    }

    @GetMapping("/verifie/{compte1Id}/{compte2Id}")
    public ResponseEntity<Boolean> verification(@PathVariable Long compte1Id, @PathVariable Long compte2Id) {
            boolean estMatch = matchService.verification(compte1Id, compte2Id);
            return ResponseEntity.ok(estMatch);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id){
        return matchService.getMatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerMatch(@PathVariable Long id){
        matchService.supprimerMatch(id);
        return ResponseEntity.noContent().build();
    }

}
