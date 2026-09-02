package model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String endereco;
    private String sexo;
    private String tipo;
    private String receberComunicados;
    private String obs;

    // getters e setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCPF() { return cpf; }
    public void setCPF(String cpf) { this.cpf = cpf; }
    public String getEndereco(){ return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getSexo() { return sexo;}
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getTipo() { return tipo;}
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getReceberComunicados() { return receberComunicados;}
    public void setReceberComunicados(String receberComunicados) { this.receberComunicados = receberComunicados; }
    public String getObs() { return obs;}
    public void setObs(String obs) { this.obs = obs; }
}
