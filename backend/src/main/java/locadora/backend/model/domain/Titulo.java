package locadora.backend.model.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class Titulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer ano;

    @Column(length = 1000)
    private String sinopse;

    @Column(nullable = false)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "diretor_id")
    private Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @ManyToMany
    @JoinTable(
        name = "titulo_ator",
        joinColumns = @JoinColumn(name = "titulo_id"),
        inverseJoinColumns = @JoinColumn(name = "ator_id")
    )
    private List<Ator> atores = new ArrayList<>();

    // Um título tem vários itens
    @OneToMany(mappedBy = "titulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();
}
