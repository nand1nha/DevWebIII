package locadora.backend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import locadora.backend.model.domain.Ator;
import locadora.backend.model.service.AtorService;

import java.util.List;

@RestController
@RequestMapping("/api/atores")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Atores", description = "Endpoints para gerenciar atores")
public class AtorController {
    
    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @GetMapping
    @Operation(summary = "Lista todos os atores")
    public List<Ator> listar() {
        return atorService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um ator por ID")
    public ResponseEntity<Ator> buscarPorId(@PathVariable Long id) {
        Ator ator = atorService.buscarPorId(id);
        if (ator == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ator);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo ator")
    public Ator salvar(@RequestBody Ator ator) {
        return atorService.salvar(ator);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ator")
    public ResponseEntity<Ator> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Ator ator) {
        if (atorService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atorService.atualizar(id, ator));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir ator")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (atorService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        atorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
