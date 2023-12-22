package br.com.targettrust.spring.aula.service.pet;

import br.com.targettrust.spring.aula.infraestructure.repository.PetRepository;
import br.com.targettrust.spring.aula.model.animal.Pet;
import br.com.targettrust.spring.aula.model.cliente.Cliente;
import br.com.targettrust.spring.aula.service.cliente.ClienteService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final ClienteService clienteService;

    @Override
    public List<Pet> getByClientId(Integer idCliente) {
        return petRepository.findAllByClienteId(idCliente);
    }

    @Override
    @Transactional
    public void createPet(Pet pet, @NotNull Integer clienteId) {
        Cliente cliente = clienteService.getById(clienteId);
        pet.setDono(cliente);
        petRepository.save(pet);

    }
}
