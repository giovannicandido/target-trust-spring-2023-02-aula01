package br.com.targettrust.spring.aula.controller;


import br.com.targettrust.spring.aula.model.Endereco;
import br.com.targettrust.spring.aula.service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public List<Endereco> getEnderecos() {
        return enderecoService.listAll();
    }

    @PostMapping
    public void saveEnderecos(@RequestBody EnderecoRequest endereco) {

        enderecoService.save(endereco.toModel(), endereco.getPessoaId());
    }

}
