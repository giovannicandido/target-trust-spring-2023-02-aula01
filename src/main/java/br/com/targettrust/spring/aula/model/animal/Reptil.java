package br.com.targettrust.spring.aula.model.animal;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Reptil extends TipoAnimal {

    private Boolean peconhento = false;

    public Reptil(String especie, Boolean peconhento) {
        this.especie = especie;
        this.peconhento = peconhento;
    }
}
