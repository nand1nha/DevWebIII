package locadora.backend.controller;


import org.springframework.web.bind.annotation.*;

import locadora.backend.model.Ator;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/atores")
@CrossOrigin(origins = "http://localhost:4200")
public class AtorController {
    private List<Ator> atores = new ArrayList<>();
    private Long idContador = 1L;

    @GetMapping
    public List<Ator> listar() {
        return atores;
    }

    @PostMapping
    public Ator salvar(@RequestBody Ator ator) {
        ator.setId(idContador++);
        atores.add(ator);
        return ator;
    }
}
