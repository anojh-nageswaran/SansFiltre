package anojh.rs.repository;

import anojh.rs.modele.Compte;
import anojh.rs.modele.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByExpediteurAndDestinataireOrDestinataireAndExpediteurOrderByDateEnvoiAsc(Compte exp, Compte dest, Compte dest1, Compte exp1);

    List<Message> findByDestinataireOrderByDateEnvoiDesc(Compte compte);

    List<Message> findByDestinataireAndLu(Compte destinataire, boolean lu);
}
