package locadora.backend.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import locadora.backend.model.domain.Classe;

@Service 
public class ClasseService {
    
    private List<Classe> classes = new ArrayList<>();
    private Long idContador = 1L;

    public List<Classe> listarTodos() {
        return classes;
    }

    public Classe salvar(Classe classe) {
        classe.setId(idContador++);
        classes.add(classe);
        return classe;
    }
}
