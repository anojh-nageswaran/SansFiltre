package anojh.rs.repository;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByCompteMatch(Compte compte, Compte compte1);

    Match existsByMatch(Compte compte1, Compte compte2, Compte compte21, Compte compte11);
}
