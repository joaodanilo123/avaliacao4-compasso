package bolsa.compasso.api.partidos.repository;

import bolsa.compasso.api.partidos.model.Associate;
import bolsa.compasso.api.partidos.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssociateRepository extends JpaRepository<Associate, Long> {
    public List<Associate> findAllByRole(Role role);
}
