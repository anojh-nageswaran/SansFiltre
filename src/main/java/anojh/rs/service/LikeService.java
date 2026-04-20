package anojh.rs.service;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Like;
import anojh.rs.modele.Match;
import anojh.rs.repository.CompteRepository;
import anojh.rs.repository.LikeRepository;
import anojh.rs.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final MatchRepository matchRepository;
    private final CompteRepository compteRepository;

    public LikeService(LikeRepository likeRepository,
                       MatchRepository matchRepository,
                       CompteRepository compteRepository) {
        this.likeRepository = likeRepository;
        this.matchRepository = matchRepository;
        this.compteRepository = compteRepository;
    }

    public Like liker(Long expediteurId, Long destinataireId, boolean estLike) {

        // On récupère les comptes depuis la BDD
        Compte expediteur = compteRepository.findById(expediteurId)
                .orElseThrow(() -> new RuntimeException("Expéditeur introuvable"));
        Compte destinataire = compteRepository.findById(destinataireId)
                .orElseThrow(() -> new RuntimeException("Destinataire introuvable"));

        // Vérification d'un potentiel like existant
        boolean dejaLike = likeRepository
                .existsByExpediteurAndDestinataire(expediteur, destinataire);
        if (dejaLike) {
            throw new RuntimeException("T'as déjà liké !");
        }

        // Création et sauvegarde du like
        Like like = new Like();
        like.setExpediteur(expediteur);
        like.setDestinataire(destinataire);
        like.setEstLike(estLike);
        like.setDateLike(LocalDateTime.now());
        likeRepository.save(like);

        // Vérification d'un potentiel match
        if (estLike) {
            boolean likeMutuel = likeRepository
                    .existsByExpediteurAndDestinataireAndEstLike(destinataire, expediteur, true);

            if (likeMutuel) {
                Match match = new Match();
                match.setCompteLiked1(expediteur);
                match.setCompteLiked2(destinataire);
                match.setDateMatch(LocalDateTime.now());
                matchRepository.save(match);
            }
        }

        return like;
    }

    public List<Like> getLikesRecus(Long destinataireId) {
        Compte destinataire = compteRepository.findById(destinataireId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        return likeRepository.findByDestinataire(destinataire);
    }

    public List<Like> getLikesEnvoyes(Long expediteurId) {
        Compte expediteur = compteRepository.findById(expediteurId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        return likeRepository.findByExpediteur(expediteur);
    }
}