package locadora.backend.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import locadora.backend.model.domain.Diretor;
import locadora.backend.model.repository.DiretorRepository;

@Service 
public class DiretorService {
    
    @Autowired 
    private DiretorRepository diretorRepository;

        public List<Diretor> listarTodos() {
        return diretorRepository.findAll();
    }

    public Diretor buscarPorId(Long id) {
        return diretorRepository.findById(id).orElse(null);
    }

    public Diretor salvar(Diretor diretor) {
        if (diretor.getId() == null || diretor.getId() == 0) {
            diretor.setId(null);
        }
        return diretorRepository.save(diretor);
    }

    public Diretor atualizar(Long id, Diretor diretor) {
        diretor.setId(id);
        return diretorRepository.save(diretor);
    }

    public void excluir(Long id) {
        diretorRepository.deleteById(id);
    }
}
