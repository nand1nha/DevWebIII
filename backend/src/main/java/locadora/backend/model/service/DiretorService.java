package locadora.backend.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import locadora.backend.model.domain.Diretor;

@Service 
public class DiretorService {
    
    private List<Diretor> diretores = new ArrayList<>();
    private Long idContador = 1L;

    public List<Diretor> listarTodos() {
        return diretores;
    }

    public Diretor salvar(Diretor diretor) {
        diretor.setId(idContador++);
        diretores.add(diretor);
        return diretor;
    }
}
