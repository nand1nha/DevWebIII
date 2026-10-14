package locadora.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import locadora.backend.model.domain.Titulo;
import locadora.backend.model.service.TituloService;

@RestController
@RequestMapping("/api/titulos")
@CrossOrigin(origins = "http://localhost:4200")
public class TituloController {
    @Autowired
    private TituloService tituloService;

    @GetMapping
    public List<Titulo> listar() {
        return tituloService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Titulo> buscarPorId(@PathVariable Long id) {
        Titulo t = tituloService.buscarPorId(id);
        return t == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(t);
    }

    @PostMapping
    public Titulo salvar(@Valid @RequestBody Titulo titulo) {
        return tituloService.salvar(titulo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Titulo> atualizar(@PathVariable Long id, @Valid @RequestBody Titulo titulo) {
        if (tituloService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tituloService.atualizar(id, titulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (tituloService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        tituloService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
