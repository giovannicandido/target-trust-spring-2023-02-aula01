package br.com.targettrust.spring.aula.controller;

import br.com.targettrust.spring.aula.controller.request.ClienteRequest;
import br.com.targettrust.spring.aula.controller.request.PagamentoRequest;
import br.com.targettrust.spring.aula.controller.response.ClienteResponse;
import br.com.targettrust.spring.aula.controller.response.PagamentoRealizadoResponse;
import br.com.targettrust.spring.aula.controller.response.PetResponse;
import br.com.targettrust.spring.aula.model.cliente.ClienteSearchParams;
import br.com.targettrust.spring.aula.service.cliente.ClienteService;
import br.com.targettrust.spring.aula.service.pet.PetService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Classe de entrada para API Rest. Controller do padrão MVC
 */
@RestController // diz ao spring que é um controlador rest
@RequestMapping(path = "/clientes") // Qual URL a classe escuta (http://localhost:8080/clientes
@RequiredArgsConstructor // lombok para criar construtor com todos os items private final
public class ClienteController {

    // vamos receber no construtor e ClienteService vai ser injeção
    // injeção de dependencias por construtor.
    private final ClienteService clienteService;

    private final PetService petService;

    @GetMapping // Esse metodo vai ser ativado se um GET for enviado para /clientes
    @Operation(description = "Lista clientes no banco de dados", summary = "Lista Clientes")
    // usando o swagger para personalizar a documentação. Acesse http://localhost:8080/swagger-ui/index.html
    public List<ClienteResponse> getClientes() {
        return clienteService.listAll()
                .stream()
            .map(ClienteResponse::of)
                .toList();
    }


    @GetMapping("/search") // esse metodo vai ser ativado se um GET for enviado para /clientes/search
    public List<ClienteResponse> filtrarPeloNome(@RequestParam(required = false, name = "nomeCliente") String name, // parametro a ser passado na url
                                                @RequestParam(required = false) Integer idade,
                                                @RequestParam(required = false) List<Integer> ids) {

        ClienteSearchParams searchParams = new ClienteSearchParams(idade, name, ids);

        return clienteService.filtrar(searchParams)
                .stream()
            .map(ClienteResponse::of)
                .toList();
    }

    @PostMapping // Esse metodo vai ser ativado se um POST for enviado para /clientes
    public void salvarClientes(@RequestBody @Valid ClienteRequest cliente // Cliente vai vir do body da requisição e vai ser um json representando a cliente.

    ) {
        clienteService.save(cliente.toModel());
    }

    @DeleteMapping("/{id}")
    // Esse metodo vai ser ativado se um DELETE for enviado para /clientes/{id} onde {id} pode ser qualquer coisa
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteById(@PathVariable(name = "id") Integer idCliente // @PathVariable pega a informação do id na url no caso em /clientes/{id}
    ) {
        clienteService.deleteById(idCliente);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    // Esse metodo vai ser ativado se um PUT for enviado para /clientes/{id} onde {id} pode ser qualquer coisa
    @ResponseStatus(HttpStatus.OK)
    // responde com um ok, nesse caso redundante pois retornamos um ok manual no final do metodo
    public ResponseEntity editarCliente(@RequestBody @Valid ClienteRequest clienteNova, @PathVariable Integer id) {
        clienteService.editCliente(clienteNova.toModel(), id);
        return ResponseEntity.ok().build(); // resposta manual
    }

    @PostMapping(path = "/{id}/pagamento")
    @ResponseStatus(HttpStatus.OK)
    public PagamentoRealizadoResponse realizarPagamento(@PathVariable("id") Integer idCliente,
                                                        @RequestBody @Valid PagamentoRequest pagamentoRequest) {

        return new PagamentoRealizadoResponse(clienteService.realizarPagamento(idCliente, pagamentoRequest.toModel()));
    }

    @GetMapping("{id}/enderecos")
    public ClienteResponse getEnderecos(
        @PathVariable("id") Integer idCliente
    ) {
        return ClienteResponse.ofDetails(
            clienteService.getById(idCliente)
        );
    }

    @GetMapping("/{id}/pets")
    public List<PetResponse> getAllPetByCliente(
        @PathVariable("id") Integer idCliente
    ) {
        return PetResponse.of(
            petService.getByClientId(idCliente)
        );
    }

}
