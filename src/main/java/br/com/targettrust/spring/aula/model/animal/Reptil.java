package br.com.targettrust.spring.aula.model.animal;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Reptil extends Animal {

    private Boolean peconhento = false;
}
