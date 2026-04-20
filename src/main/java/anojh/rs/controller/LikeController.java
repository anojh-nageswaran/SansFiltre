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

    @PostMapping("/{expediteurId}/{destinataireId}/{estLike}")
    public ResponseEntity<Like> liker(
            @PathVariable Long expediteurId,
            @PathVariable Long destinataireId,
            @PathVariable boolean estLike) {
        Like nouveauLike = likeService.liker(expediteurId, destinataireId, estLike);
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
