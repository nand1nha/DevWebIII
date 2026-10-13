package locadora.backend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import locadora.backend.model.domain.Classe;

@Repository 
public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
