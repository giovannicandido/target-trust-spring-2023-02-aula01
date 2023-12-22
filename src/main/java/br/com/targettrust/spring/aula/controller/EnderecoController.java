package br.com.targettrust.spring.aula.controller;


import br.com.targettrust.spring.aula.controller.request.EnderecoRequest;
import br.com.targettrust.spring.aula.controller.response.EnderecoResponse;
import br.com.targettrust.spring.aula.model.cliente.Endereco;
import br.com.targettrust.spring.aula.service.cliente.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    /**
     * Nao vamos retornar a entidade direto, vimos o problemas que pode causar.
     * Vamos sempre retornar uma Response de api (famoso DTO - Data Transfer Object)
     *
     * @return Lista de EnderecoResponse que é nosso modelo de API e não de banco de dados
     */
    @GetMapping
    public List<EnderecoResponse> getEnderecos() {
        return enderecoService.listAll()
                .stream().map(EnderecoResponse::of)
                .toList();
    }

    @GetMapping("/search")
    public List<EnderecoResponse> searchEnderecos(@RequestParam(required = false) String rua,
                                                  @RequestParam(required = false) String nomeCliente) {

        List<Endereco> enderecos;

        if (!Objects.isNull(rua)) {
            enderecos = enderecoService.searchByRua(rua);
        } else {
            enderecos = enderecoService.searchByNomeCliente(nomeCliente);
        }


        return enderecos
                .stream().map(EnderecoResponse::of)
                .toList();
    }

    /**
     * Salvamos um endereço, também não recebemos a entidade e sim um modelo de API
     * A anotação @Valid vai validar a request. Veja {@link EnderecoRequest} onde tem anotações de validações as propriedades
     *
     * @param endereco
     */
    @PostMapping
    public void saveEnderecos(@RequestBody @Valid EnderecoRequest endereco) {

        enderecoService.save(endereco.toModel(), endereco.getClienteId());
    }

}
