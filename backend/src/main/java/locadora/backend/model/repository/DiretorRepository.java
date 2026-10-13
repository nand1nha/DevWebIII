package locadora.backend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import locadora.backend.model.domain.Diretor;

@Repository 
public interface DiretorRepository extends JpaRepository<Diretor, Long> {

}
