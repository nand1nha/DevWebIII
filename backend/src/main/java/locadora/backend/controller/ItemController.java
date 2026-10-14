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
import locadora.backend.model.domain.Item;
import locadora.backend.model.service.ItemService;

@RestController
@RequestMapping("/api/itens")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> listar() {
        return itemService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        Item i = itemService.buscarPorId(id);
        return i == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(i);
    }

    @GetMapping("/titulo/{tituloId}")
    public List<Item> listarPorTitulo(@PathVariable Long tituloId) {
        return itemService.listarPorTitulo(tituloId);
    }

    @PostMapping
    public Item salvar(@Valid @RequestBody Item item) {
        return itemService.salvar(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizar(@PathVariable Long id, @Valid @RequestBody Item item) {
        if (itemService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(itemService.atualizar(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (itemService.buscarPorId(id) == null) return ResponseEntity.notFound().build();
        itemService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
