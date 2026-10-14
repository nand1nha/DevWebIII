package locadora.backend.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import locadora.backend.model.domain.Classe;
import locadora.backend.model.service.ClasseService;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Classes", description = "Endpoints para gerenciar classes (categorias de preço/prazo)")
public class ClasseController {
    
    @Autowired
    private ClasseService classeService; 

    @GetMapping
    @Operation(summary = "Lista todos as classes")
    public List<Classe> listar() {
        return classeService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma classe por ID")
    public ResponseEntity<Classe> buscarPorId(@PathVariable Long id) {
        Classe classe = classeService.buscarPorId(id);
        if (classe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classe);
    }

    @PostMapping
    @Operation(summary = "Cadastra uma nova classe")
    public Classe salvar(@Valid @RequestBody Classe classe) {
        return classeService.salvar(classe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar classe")
    public ResponseEntity<Classe> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Classe classe) {
        if (classeService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classeService.atualizar(id, classe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir classe")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (classeService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        classeService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
