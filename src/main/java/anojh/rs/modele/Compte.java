package anojh.rs.modele;

import anojh.rs.enums.Genre;
import anojh.rs.enums.Religion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@Entity
@Table(name = "comptes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private int age;
    private String ville;
    private String Pays;
    private String metier;
    private String email;
    private String mp;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Religion religion;

    private boolean pratiquant;
    private String langue;
    private String natchathiram;
    private String description;

    @ElementCollection
    private List<String> photos;
}
