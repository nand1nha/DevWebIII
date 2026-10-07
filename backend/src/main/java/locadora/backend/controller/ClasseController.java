package locadora.backend.controller;



import org.springframework.web.bind.annotation.*;

import locadora.backend.model.domain.Classe;
import locadora.backend.model.service.ClasseService;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClasseController {
    
    private final ClasseService classeService = new ClasseService(); 

    @GetMapping
    public List<Classe> listar() {
        return classeService.listarTodos(); 
    }

    @PostMapping
    public Classe salvar(@RequestBody Classe classe) {
        return classeService.salvar(classe);
    }
}
