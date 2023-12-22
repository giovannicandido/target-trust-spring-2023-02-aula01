package br.com.targettrust.spring.aula.controller.request;

import br.com.targettrust.spring.aula.model.animal.Mamifero;
import br.com.targettrust.spring.aula.model.animal.Peixe;
import br.com.targettrust.spring.aula.model.animal.Pet;
import br.com.targettrust.spring.aula.model.animal.Reptil;
import br.com.targettrust.spring.aula.model.animal.TipoAnimal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class PetRequest {
    @NotBlank
    private String nome;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    @Valid
    private TipoAnimalRequest tipoAnimal;

    @NotNull
    private Integer clienteId;

    public Pet toModel() {
        return Pet.builder()
            .dataNascimento(dataNascimento)
            .nome(nome)
            .tipoAnimal(discoverTipoAnimal())
            .build();
    }

    private TipoAnimal discoverTipoAnimal() {
        switch (tipoAnimal.getTipo()) {
            case REPTIL:
                return new Reptil(tipoAnimal.getEspecie(), tipoAnimal.getInformacoes().getPeconhento());
            case PEIXE:
                return new Peixe(tipoAnimal.getEspecie(), tipoAnimal.getInformacoes().getTipoAgua());
            case MAMIFERO:
                return new Mamifero(tipoAnimal.getEspecie(), tipoAnimal.getInformacoes().getRaca(), tipoAnimal.getInformacoes().getPossuiPelos());
            default:
                throw new RuntimeException("Tipo de animal passado não existe no sistema");
        }
    }

}
