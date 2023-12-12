package br.com.targettrust.spring.aula.model.animal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Peixe extends TipoAnimal {

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private TipoAgua tipoAgua;

    public Peixe(String especie, TipoAgua tipoAgua) {
        this.especie = especie;
        this.tipoAgua = tipoAgua;
    }
}
