package anojh.rs.service;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Match;
import anojh.rs.repository.CompteRepository;
import anojh.rs.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final CompteRepository compteRepository;

    public MatchService(MatchRepository matchRepository, CompteRepository compteRepository) {
        this.matchRepository = matchRepository;
        this.compteRepository = compteRepository;
    }

    public List<Match> getMatchs(Long compteid) {
        Compte compte = compteRepository.findById(compteid)
                .orElseThrow(() -> new RuntimeException("Expéditeur introuvable"));
        return matchRepository.findByCompteLiked1OrCompteLiked2(compte, compte);
    }

    public boolean verification(Long compte1Id,  Long compte2Id) {
        Compte compte1 = compteRepository.findById(compte1Id)
                .orElseThrow(() -> new RuntimeException("Expéditeur introuvable"));
        Compte compte2 = compteRepository.findById(compte2Id)
                .orElseThrow(() -> new RuntimeException("Expéditeur introuvable"));
        return matchRepository.existsByCompteLiked1AndCompteLiked2OrCompteLiked2AndCompteLiked1(compte1, compte2, compte2, compte1);
    }

    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public void supprimerMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
