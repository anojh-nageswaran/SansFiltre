package anojh.rs.modele;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expediteur_id")
    private Compte expediteur;

    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    private Compte destinataire;

    private boolean estLike;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;
}
