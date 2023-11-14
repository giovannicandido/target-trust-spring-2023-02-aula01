package br.com.targettrust.spring.aula.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@SequenceGenerator(name = "pessoa_seq", initialValue = 1, allocationSize = 1)
public class Pessoa {

    @Id
    @GeneratedValue(generator = "pessoa_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;

    @OneToMany
    private List<Endereco> enderecos;

    @OneToOne
    private CartaoCredito cartaoCredito;

    public Pessoa(Integer id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }
}
