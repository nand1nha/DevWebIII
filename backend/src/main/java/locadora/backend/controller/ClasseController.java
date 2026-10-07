package locadora.backend.controller;



import org.springframework.web.bind.annotation.*;

import locadora.backend.model.Classe;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClasseController {
    private List<Classe> classes = new ArrayList<>();
    private Long idContador = 1L;

    @GetMapping
    public List<Classe> listar() {
        return classes;
    }

    @PostMapping
    public Classe salvar(@RequestBody Classe classe) {
        classe.setId(idContador++);
        classes.add(classe);
        return classe;
    }
}
