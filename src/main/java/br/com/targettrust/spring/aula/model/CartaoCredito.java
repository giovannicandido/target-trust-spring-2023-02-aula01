package br.com.targettrust.spring.aula.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CartaoCredito {

    @Id
    private Long id;
    private String numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
