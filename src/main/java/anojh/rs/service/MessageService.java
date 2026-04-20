package anojh.rs.service;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Message;
import anojh.rs.repository.CompteRepository;
import anojh.rs.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final CompteRepository compteRepository;
    private final MatchService matchService;

    public MessageService(MessageRepository messageRepository, CompteRepository compteRepository, MatchService matchService) {
        this.messageRepository = messageRepository;
        this.compteRepository = compteRepository;
        this.matchService = matchService;
    }

    public Message envoyerMessage(Long DestinataireId, Long ExpediteurId, String contenu) {
        Compte destinataire = compteRepository.findById(DestinataireId)
                .orElseThrow(() -> new RuntimeException("Destinataire introuvable"));

        Compte expediteur = compteRepository.findById(ExpediteurId)
                .orElseThrow(() -> new RuntimeException("Expéditeur introuvable"));

        boolean verifie = matchService.verification(DestinataireId, ExpediteurId);

        if(!verifie) {
            throw new RuntimeException("Vous n'avez pas match avec la personne.");
        }

        Message message = new Message();
        message.setDestinataire(destinataire);
        message.setExpediteur(expediteur);
        message.setDateEnvoi(LocalDateTime.now());
        message.setLu(false);
        return messageRepository.save(message);
    }

    public List<Message> getConversation(Long DestinataireId, Long ExpediteurId) {
        Compte dest = compteRepository.findById(DestinataireId)
                .orElseThrow(() -> new RuntimeException("Destinataire introuvable"));

        Compte exp = compteRepository.findById(ExpediteurId)
                .orElseThrow(() -> new RuntimeException("Expéditeur introuvable"));

        return messageRepository.findByExpediteurAndDestinataireOrDestinataireAndExpediteurOrderByDateEnvoiAsc(exp, dest, dest, exp);
    }

    public List<Message> getBoitedeReception(Long id) {
        Compte compte  = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        return messageRepository.findByDestinataireOrderByDateEnvoiDesc(compte);
    }

    public Message lu(Long MessageId) {
        Message message = messageRepository.findById(MessageId)
                .orElseThrow(() -> new RuntimeException("Message introubale"));

        message.setLu(true);
        return messageRepository.save(message);
    }

    public List<Message> getNonLu(Long DestinataireId) {
        Compte destinataire = compteRepository.findById(DestinataireId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        return messageRepository.findByDestinataireAndLu(destinataire, false);
    }

    public void supprimerMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
