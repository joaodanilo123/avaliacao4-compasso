package bolsa.compasso.api.partidos.repository;

import bolsa.compasso.api.partidos.model.Party;
import bolsa.compasso.api.partidos.model.Ideology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {
    public List<Party> findAllByIdeology(Ideology ideology);
}
