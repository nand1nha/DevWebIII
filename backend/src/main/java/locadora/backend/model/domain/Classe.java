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
@Schema(description = "Representa uma classe do acervo")
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único da classe", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nome da classe", example = "Lançamento")
    private String nome;

    @Column(nullable = false)
    @Schema(description = "Valor da locação em reais", example = "5.0")
    private Double valor;
    
    @Column(nullable = false)
    @Schema(description = "Prazo de devolução em dias", example = "2.0")
    private Double prazoDevolucao;
}
