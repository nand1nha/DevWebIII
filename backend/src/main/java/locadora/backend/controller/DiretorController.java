package locadora.backend.controller;


import org.springframework.web.bind.annotation.*;

import locadora.backend.model.domain.Diretor;
import locadora.backend.model.service.DiretorService;

import java.util.List;

@RestController
@RequestMapping("/api/diretores")
@CrossOrigin(origins = "http://localhost:4200")
public class DiretorController {
    
    private final DiretorService diretorService = new DiretorService(); 

    @GetMapping
    public List<Diretor> listar() {
        return diretorService.listarTodos(); 
    }

    @PostMapping
    public Diretor salvar(@RequestBody Diretor diretor) {
        return diretorService.salvar(diretor);
    }
}
