package anojh.rs.repository;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByExpediteurAndDestinataire(Compte expediteur, Compte destinataire);

    boolean existsByExpediteurAndDestinataireAndEstLike(Compte expediteur, Compte destinataire, boolean estLike);

    List<Like> findByDestinataire(Compte destinataire);

    List<Like> findByExpediteur(Compte expediteur);
}
