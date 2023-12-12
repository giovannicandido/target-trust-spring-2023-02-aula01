package br.com.targettrust.spring.aula.controller.response;

import br.com.targettrust.spring.aula.model.animal.Pet;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@Builder
public class PetResponse {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;

    public Long getIdade() {
        return ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
    }

    public static PetResponse of(Pet pet) {
        return PetResponse.builder()
            .nome(pet.getNome())
            .id(pet.getId())
            .dataNascimento(pet.getDataNascimento())
            .build();
    }

    public static List<PetResponse> of(List<Pet> pet) {
        return pet.stream().map(PetResponse::of).toList();
    }

}
