package locadora.backend.controller;


import org.springframework.http.HttpStatus;
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
    public Ator buscarPorId(@PathVariable Long id) {
        return atorService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo ator")
    public ResponseEntity<Ator> salvar(@Valid @RequestBody Ator ator) {
        Ator salvo = atorService.salvar(ator);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ator")
    public Ator atualizar(@PathVariable Long id, @Valid @RequestBody Ator ator) {
        return atorService.atualizar(id, ator);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir ator")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        atorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
