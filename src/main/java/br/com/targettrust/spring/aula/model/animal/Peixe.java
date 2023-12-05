package br.com.targettrust.spring.aula.model.animal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Peixe extends Animal {

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private TipoAgua tipoAgua;
}
