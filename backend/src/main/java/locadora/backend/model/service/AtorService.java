package locadora.backend.model.service;


import org.springframework.stereotype.Service;

import locadora.backend.model.domain.Ator;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtorService {

    private List<Ator> atores = new ArrayList<>();
    private Long idContador = 1L;

    public List<Ator> listarTodos() {
        return atores;
    }

    public Ator salvar(Ator ator) {
        ator.setId(idContador++);
        atores.add(ator);
        return ator;
    }
}
