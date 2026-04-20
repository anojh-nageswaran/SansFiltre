package anojh.rs.controller;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Like;
import anojh.rs.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@AllArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{expediteurId}/{destinataireId}/{aime}")
    public ResponseEntity<Like> liker(
            @PathVariable Long expediteurId,
            @PathVariable Long destinataireId,
            @PathVariable boolean aime) {
        Like nouveauLike = likeService.liker(expediteurId, destinataireId, aime);
        return ResponseEntity.status(201).body(nouveauLike);
    }

    @GetMapping("/recus/{compteId}")
    public List<Like> getLikesRecus(@PathVariable Long compteId) {
        return likeService.getLikesRecus(compteId);
    }

    @GetMapping("/envoyes/{compteId}")
    public List<Like> getLikesEnvoyes(@PathVariable Long compteId) {
        return likeService.getLikesEnvoyes(compteId);
    }
}
