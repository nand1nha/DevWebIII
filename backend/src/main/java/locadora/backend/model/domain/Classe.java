package locadora.backend.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Classe {
    private Long id;
    private String nome;
    private Double valor;
    private Double prazoDevolucao;
}
