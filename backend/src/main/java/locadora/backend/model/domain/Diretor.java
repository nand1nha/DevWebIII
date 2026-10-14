package locadora.backend.model.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity 
@Schema(description = "Representa um diretor do acervo")
public class Diretor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do diretor", example = "1")
    private Long id;
    
    @Column(nullable = false)
    @Schema(description = "Nome completo do diretor", example = "Keanu Reeves")
    private String nome;
}
