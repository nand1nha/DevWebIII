package locadora.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import locadora.backend.model.domain.Ator;
import locadora.backend.model.service.AtorService;

import java.util.List;

@RestController
@RequestMapping("/api/atores")
@CrossOrigin(origins = "http://localhost:4200")
public class AtorController {
    
    private final AtorService atorService = new AtorService(); 

    @GetMapping
    public List<Ator> listar() {
        return atorService.listarTodos(); 
    }

    @PostMapping
    public Ator salvar(@RequestBody Ator ator) {
        return atorService.salvar(ator);
    }
}
