package locadora.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import locadora.backend.model.domain.Diretor;
import locadora.backend.model.service.DiretorService;

import java.util.List;

@RestController
@RequestMapping("/api/diretores")
@CrossOrigin(origins = "http://localhost:4200")
public class DiretorController {
    
    @Autowired
    private DiretorService diretorService; 

    @GetMapping
    public List<Diretor> listar() {
        return diretorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> buscarPorId(@PathVariable Long id) {
        Diretor diretor = diretorService.buscarPorId(id);
        if (diretor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diretor);
    }

    @PostMapping
    public Diretor salvar(@Valid @RequestBody Diretor diretor) {
        return diretorService.salvar(diretor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diretor> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Diretor diretor) {
        if (diretorService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diretorService.atualizar(id, diretor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (diretorService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        diretorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
