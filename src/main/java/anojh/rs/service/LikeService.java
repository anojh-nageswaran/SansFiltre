package anojh.rs.service;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Like;
import anojh.rs.modele.Match;
import anojh.rs.repository.LikeRepository;
import anojh.rs.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final MatchRepository matchRepository;


    public LikeService(LikeRepository likeRepository, MatchRepository matchRepository) {
        this.likeRepository = likeRepository;
        this.matchRepository = matchRepository;
    }

    public Like liker(Compte expediteur, Compte destinataire, boolean estLike) {

        boolean dejalike = likeRepository.existsByExpediteurAndDestinataire(expediteur, destinataire);

        if (dejalike) {
            throw new RuntimeException("T'as déjà liké !");
        }

        Like like = new Like();
        like.setDestinataire(destinataire);
        like.setExpediteur(expediteur);
        like.setEstLike(estLike);
        like.setDateLike(LocalDateTime.now());
        likeRepository.save(like);

        if (estLike) {
            boolean likeMutuel = likeRepository.existsByExpediteurAndDestinataireAndEstLike(expediteur, destinataire, true);

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

    public List<Like> getLikeRecus(Compte destinataire) {
        return likeRepository.findByDestinataire(destinataire);
    }

    public List<Like> getLikeEnvoyes(Compte expediteur) {
        return likeRepository.findByExpediteur(expediteur);
    }

}
