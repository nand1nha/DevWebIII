package locadora.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import locadora.backend.model.domain.Diretor;
import locadora.backend.model.service.DiretorService;

import java.util.List;

@RestController
@RequestMapping("/api/diretores")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Diretores", description = "Endpoints para gerenciar diretores")
public class DiretorController {
    
    @Autowired
    private DiretorService diretorService; 

    @GetMapping
    @Operation(summary = "Lista todos os diretores")
    public List<Diretor> listar() {
        return diretorService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um diretor por ID")
    public ResponseEntity<Diretor> buscarPorId(@PathVariable Long id) {
        Diretor diretor = diretorService.buscarPorId(id);
        if (diretor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diretor);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo diretor")
    public Diretor salvar(@Valid @RequestBody Diretor diretor) {
        return diretorService.salvar(diretor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar diretor")
    public ResponseEntity<Diretor> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Diretor diretor) {
        if (diretorService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diretorService.atualizar(id, diretor));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir diretor")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (diretorService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        diretorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
