package br.com.targettrust.spring.aula.service.pet;

import br.com.targettrust.spring.aula.model.animal.Pet;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface PetService {
    List<Pet> getByClientId(Integer idCliente);

    void createPet(Pet model, @NotNull Integer clienteId);
}
