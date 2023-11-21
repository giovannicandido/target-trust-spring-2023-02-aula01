package br.com.targettrust.spring.aula.controller;


import br.com.targettrust.spring.aula.controller.request.EnderecoRequest;
import br.com.targettrust.spring.aula.controller.response.EnderecoResponse;
import br.com.targettrust.spring.aula.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoResponse> getEnderecos() {
        return enderecoService.listAll()
                .stream().map(EnderecoResponse::of)
                .toList();
    }

    @PostMapping
    public void saveEnderecos(@RequestBody @Valid EnderecoRequest endereco) {

        enderecoService.save(endereco.toModel(), endereco.getPessoaId());
    }

}