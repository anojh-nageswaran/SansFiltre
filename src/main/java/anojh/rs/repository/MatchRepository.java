package anojh.rs.repository;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByCompteLiked1OrCompteLiked2(Compte compte1, Compte compte2);

    boolean existsByCompteLiked1AndCompteLiked2OrCompteLiked2AndCompteLiked1(
            Long compteLiked1, Long compteLiked2,
            Long compteLiked21, Long compteLiked11);
}
