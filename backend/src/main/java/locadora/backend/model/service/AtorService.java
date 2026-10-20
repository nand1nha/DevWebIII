package locadora.backend.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import locadora.backend.exception.RecursoNaoEncontradoException;
import locadora.backend.exception.RegraNegocioException;
import locadora.backend.model.domain.Ator;
import locadora.backend.model.repository.AtorRepository;

import java.util.List;

@Service
public class AtorService {

    private final AtorRepository atorRepository;

    public AtorService(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    public List<Ator> listarTodos() {
        return atorRepository.findAll();
    }

    public Ator salvar(Ator ator) {
        ator.setId(null);
        validarNomeUnico(ator.getNome(), null);
        return atorRepository.save(ator);
    }

    public Ator atualizar(Long id, Ator ator) {
        Ator existente = buscarPorId(id);   // já lança 404 se não existir
        validarNomeUnico(ator.getNome(), id);
        existente.setNome(ator.getNome());
        return atorRepository.save(existente);
    }

    public void excluir(Long id) {
        Ator ator = buscarPorId(id);        // já lança 404
        atorRepository.delete(ator);
    }

    public Ator buscarPorId(Long id) {
        return atorRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Ator", id));
    }

    private void validarNomeUnico(String nome, Long idAtual) {
        boolean duplicado = (idAtual == null)
                ? atorRepository.existsByNome(nome)
                : atorRepository.existsByNomeAndIdNot(nome, idAtual);

        if (duplicado) {
            throw new RegraNegocioException(
                    "Já existe um ator cadastrado com o nome: " + nome);
        }
    }
}
