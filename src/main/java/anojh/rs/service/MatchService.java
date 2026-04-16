package anojh.rs.service;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Match;
import anojh.rs.repository.LikeRepository;
import anojh.rs.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getMatchs(Compte compte) {
        return matchRepository.findByCompteMatch(compte, compte);
    }

    public Match verification(Compte compte1, Compte compte2) {
        return matchRepository.existsByMatch(compte1, compte2, compte2, compte1);
    }

    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public void supprimerMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
