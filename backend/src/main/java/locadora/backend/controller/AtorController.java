package locadora.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import locadora.backend.model.domain.Ator;
import locadora.backend.model.domain.Classe;
import locadora.backend.model.service.AtorService;

import java.util.List;

@RestController
@RequestMapping("/api/atores")
@CrossOrigin(origins = "http://localhost:4200")
public class AtorController {
    
    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @GetMapping
    public List<Ator> listar() {
        return atorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> buscarPorId(@PathVariable Long id) {
        Ator ator = atorService.buscarPorId(id);
        if (ator == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ator);
    }

    @PostMapping
    public Ator salvar(@RequestBody Ator ator) {
        return atorService.salvar(ator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ator> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Ator ator) {
        if (atorService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atorService.atualizar(id, ator));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (atorService.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        atorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
