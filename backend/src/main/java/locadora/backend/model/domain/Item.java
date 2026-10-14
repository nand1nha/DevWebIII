package locadora.backend.model.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numSerie;


    @Column(nullable = false)
    private LocalDate dtAquisicao;

    @NotBlank(message = "O tipo do item é obrigatório")
    @Column(nullable = false)
    private String tipoItem;

    @ManyToOne
    @JoinColumn(name = "titulo_id", nullable = false)
    @JsonIgnoreProperties({"itens", "atores", "diretor", "classe"})
    private Titulo titulo;
}
