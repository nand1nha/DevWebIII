package locadora.backend.model.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Schema(description = "Representa um titulo do acervo")
public class Titulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do titulo", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nome do título", example = "Matrix")
    private String nome;

    @Column(nullable = false)
    @Schema(description = "Ano de lançamento", example = "1999")
    private Integer ano;

    @Column(length = 1000)
    @Schema(description = "Sinopse do título", example = "Um hacker descobre a verdade sobre sua realidade.")
    private String sinopse;

    @Column(nullable = false)
    @Schema(description = "Categoria/gênero do título", example = "Ficção")
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "diretor_id")
    @Schema(description = "Diretor do título")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    @Schema(description = "Classe de locação do título")
    private Classe classe;

    @ManyToMany
    @JoinTable(
        name = "titulo_ator",
        joinColumns = @JoinColumn(name = "titulo_id"),
        inverseJoinColumns = @JoinColumn(name = "ator_id")
    )
    @Schema(description = "Atores que participam do título")
    private List<Ator> atores = new ArrayList<>();

    // Um título tem vários itens
    @OneToMany(mappedBy = "titulo", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Itens físicos (cópias) deste título")
    private List<Item> itens = new ArrayList<>();
}
