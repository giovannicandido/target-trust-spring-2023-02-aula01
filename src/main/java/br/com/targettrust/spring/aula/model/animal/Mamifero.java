package br.com.targettrust.spring.aula.model.animal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Mamifero extends TipoAnimal {

    @Column(nullable = false)
    private String raca;

    private Boolean possuiPelos;

    public Mamifero(String especie, String raca, Boolean possuiPelos) {
        this.especie = especie;
        this.raca = raca;
        this.possuiPelos = possuiPelos;
    }
}
