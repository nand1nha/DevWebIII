package locadora.backend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import locadora.backend.model.domain.Ator;

@Repository 
public interface AtorRepository extends JpaRepository<Ator, Long> {
    
    boolean existsByNome(String nome);

    boolean existsByNomeAndIdNot(String nome, Long id);

}