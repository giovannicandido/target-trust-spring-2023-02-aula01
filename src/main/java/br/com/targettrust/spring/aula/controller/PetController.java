package br.com.targettrust.spring.aula.controller;

import br.com.targettrust.spring.aula.controller.request.PetRequest;
import br.com.targettrust.spring.aula.service.pet.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPet(@RequestBody @Valid PetRequest petRequest) {
        petService.createPet(petRequest.toModel(), petRequest.getClienteId());
    }

}
