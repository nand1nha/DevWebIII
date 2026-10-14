package locadora.backend.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import locadora.backend.model.domain.Ator;
import locadora.backend.model.repository.AtorRepository;

import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    public List<Ator> listarTodos() {
        return atorRepository.findAll();
    }

    public Ator salvar(Ator ator) {
        if (ator.getId() == null || ator.getId() == 0) {
            ator.setId(null);
        }
        return atorRepository.save(ator);
    }

    public Ator atualizar(Long id, Ator ator) {
        ator.setId(id);   // usa o id da URL
        return atorRepository.save(ator);
    }

    public void excluir(Long id) {
        atorRepository.deleteById(id);
    }

    public Ator buscarPorId(Long id) {
        return atorRepository.findById(id).orElse(null);
    }
}
