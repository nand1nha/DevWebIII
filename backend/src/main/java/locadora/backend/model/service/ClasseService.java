package locadora.backend.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import locadora.backend.model.domain.Classe;
import locadora.backend.model.repository.ClasseRepository;

@Service 
public class ClasseService {
    
    @Autowired 
    private ClasseRepository classeRepository;

        public List<Classe> listarTodos() {
        return classeRepository.findAll();
    }

    public Classe buscarPorId(Long id) {
        return classeRepository.findById(id).orElse(null);
    }

    public Classe salvar(Classe classe) {
        // 🔒 Blindagem: força INSERT se o id vier nulo ou 0
        if (classe.getId() == null || classe.getId() == 0) {
            classe.setId(null);
        }
        return classeRepository.save(classe);
    }

    public Classe atualizar(Long id, Classe classe) {
        classe.setId(id);
        return classeRepository.save(classe);
    }

    public void excluir(Long id) {
        classeRepository.deleteById(id);
    }
}
