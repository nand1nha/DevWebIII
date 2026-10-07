package locadora.backend.controller;


import org.springframework.web.bind.annotation.*;

import locadora.backend.model.Diretor;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/diretores")
@CrossOrigin(origins = "http://localhost:4200")
public class DiretorController {
    private List<Diretor> diretores = new ArrayList<>();
    private Long idContador = 1L;

    @GetMapping
    public List<Diretor> listar() {
        return diretores;
    }

    @PostMapping
    public Diretor salvar(@RequestBody Diretor diretor) {
        diretor.setId(idContador++);
        diretores.add(diretor);
        return diretor;
    }
}
