package anojh.rs.controller;

import anojh.rs.modele.Message;
import anojh.rs.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messagerie")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/envoyer/{expediteurId}/{destinataireId}")
    public ResponseEntity<Message> envoyerMessage(
            @PathVariable Long expediteurId,
            @PathVariable Long destinataireId,
            @RequestBody String contenu
    ) {
        Message nouveaumessage = messageService.envoyerMessage(destinataireId, expediteurId, contenu);
        return ResponseEntity.status(201).body(nouveaumessage);
    }

    @PostMapping("/conversation/{DestinataireId}/{ExpediteurId}")
    public List<Message> getConversation(
            @PathVariable Long DestinataireId,
            @PathVariable Long ExpediteurId
    ) {
        return messageService.getConversation(DestinataireId, ExpediteurId);
    }

    @PostMapping("/reception/{compteid}")
    public List<Message> getBoitedeReception(@PathVariable Long compteid) {
        return messageService.getBoitedeReception(compteid);
    }

    @PutMapping("/lu/{messageid}")
    public ResponseEntity<Message> lu(@PathVariable Long messageid) {
        Message lu = messageService.lu(messageid);
        return ResponseEntity.status(201).body(lu);
    }

    @PostMapping("/nonlu/{DestinataireId}")
    public List<Message> getNonLu(@PathVariable Long DestinataireId) {
        return messageService.getNonLu(DestinataireId);
    }

    @DeleteMapping("/supprimer/{DestinataireId}")
    public ResponseEntity<Void> supprimerMessage(@PathVariable Long DestinataireId) {
        messageService.supprimerMessage(DestinataireId);
        return ResponseEntity.noContent().build();
    }
}
