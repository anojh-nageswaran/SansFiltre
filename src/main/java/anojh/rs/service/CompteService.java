package anojh.rs.service;

import anojh.rs.modele.Compte;
import anojh.rs.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    public CompteService (CompteRepository compteRepository){
        this.compteRepository = compteRepository;
    }

    public Compte creer(Compte compte) {
        return compteRepository.save(compte);
    }

    public List<Compte> getAllComptes(){
        return compteRepository.findAll();
    }

    public Optional<Compte> getCompte(Long id) {
        return compteRepository.findById(id);
    }

    public Compte setCompte(Long id, Compte comptemodifie) {
        Compte compteExiste = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        compteExiste.setNom(comptemodifie.getNom());
        compteExiste.setPrenom(comptemodifie.getPrenom());
        compteExiste.setNatchathiram(comptemodifie.getNatchathiram());
        compteExiste.setReligion(comptemodifie.getReligion());
        compteExiste.setNom(comptemodifie.getNom());
        compteExiste.setAge(comptemodifie.getAge());
        compteExiste.setVille(comptemodifie.getVille());
        compteExiste.setPays(comptemodifie.getPays());
        compteExiste.setMetier(comptemodifie.getMetier());
        compteExiste.setLangue(comptemodifie.getLangue());
        compteExiste.setNatchathiram(comptemodifie.getNatchathiram());
        compteExiste.setDescription(comptemodifie.getDescription());
        compteExiste.setGenre(comptemodifie.getGenre());
        compteExiste.setPratiquant(comptemodifie.isPratiquant());
        compteExiste.setPhotos(comptemodifie.getPhotos());

        return compteRepository.save(compteExiste);
    }

    public void supprimer(Long id) {
        compteRepository.deleteById(id);
    }
}
