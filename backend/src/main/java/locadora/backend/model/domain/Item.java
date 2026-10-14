package locadora.backend.model.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Schema(description = "Representa um item do acervo")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do item", example = "1")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Número de série único do item", example = "1001")
    private Integer numSerie;


    @Column(nullable = false)
    @Schema(description = "Data em que o item foi adquirido", example = "2024-01-15")
    private LocalDate dtAquisicao;

    @NotBlank(message = "O tipo do item é obrigatório")
    @Column(nullable = false)
    @Schema(
        description = "Tipo físico do item",
        example = "DVD",
        allowableValues = {"DVD", "Blu-ray", "VHS", "Fita Cassete"}
    )
    private String tipoItem;

    @ManyToOne
    @JoinColumn(name = "titulo_id", nullable = false)
    @JsonIgnoreProperties({"itens", "atores", "diretor", "classe"})
    @Schema(description = "Título ao qual este item pertence")
    private Titulo titulo;
}
