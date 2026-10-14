package locadora.backend.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import locadora.backend.model.domain.Titulo;
import locadora.backend.model.repository.TituloRepository;

@Service 
public class TituloService {
    
    @Autowired
    private TituloRepository tituloRepository;

    public List<Titulo> listarTodos() {
        return tituloRepository.findAll();
    }

    public Titulo buscarPorId(Long id) {
        return tituloRepository.findById(id).orElse(null);
    }

    public Titulo salvar(Titulo titulo) {
        if (titulo.getId() == null || titulo.getId() == 0) {
            titulo.setId(null);
        }
        return tituloRepository.save(titulo);
    }

    public Titulo atualizar(Long id, Titulo titulo) {
        titulo.setId(id);
        return tituloRepository.save(titulo);
    }

    public void excluir(Long id) {
        tituloRepository.deleteById(id);
    }
}
