package anojh.rs.service;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Match;
import anojh.rs.repository.LikeRepository;
import anojh.rs.repository.MatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getMatchs(Compte compte) {
        return matchRepository.findByCompteLiked1OrCompteLiked2(compte, compte);
    }

    public boolean verification(Long compte1Id,  Long compte2Id) {
        return matchRepository.existsByCompteLiked1AndCompteLiked2OrCompteLiked2AndCompteLiked1(compte1Id, compte2Id, compte2Id, compte1Id);
    }

    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public void supprimerMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
