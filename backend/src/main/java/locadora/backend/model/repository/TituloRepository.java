package locadora.backend.model.repository;

import locadora.backend.model.domain.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface TituloRepository extends JpaRepository<Titulo, Long>{

}
