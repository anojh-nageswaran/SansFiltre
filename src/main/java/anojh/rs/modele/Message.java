package anojh.rs.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="messagerie")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenu;
    private LocalDateTime dateEnvoi;
    private boolean lu;

    @ManyToOne
    @JoinColumn(name = "expediteur_id")
    private Compte expediteur;

    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    private Compte destinataire;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
