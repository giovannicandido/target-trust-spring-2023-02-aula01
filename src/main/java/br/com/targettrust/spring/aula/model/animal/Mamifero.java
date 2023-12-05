package br.com.targettrust.spring.aula.model.animal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Mamifero extends Animal {

    @Column(nullable = false)
    private String raca;

    private Boolean possuiPelos;
}
