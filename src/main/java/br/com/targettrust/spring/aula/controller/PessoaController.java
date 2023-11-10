package br.com.targettrust.spring.aula.controller;

import br.com.targettrust.spring.aula.model.FilterSearchParams;
import br.com.targettrust.spring.aula.model.Pessoa;
import br.com.targettrust.spring.aula.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe de entrada para API Rest. Controller do padrão MVC
 */
@RestController // diz ao spring que é um controlador rest
@RequestMapping(path = "/pessoas") // Qual URL a classe escuta (http://localhost:8080/pessoas
public class PessoaController {

    // vamos receber no construtor e PessoaService vai ser injeção
    private final PessoaService pessoaService;

    // injeção de dependencias por construtor.
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping() // Esse metodo vai ser ativado se um GET for enviado para /pessoas
    @Operation(description = "Lista pessoas no banco de dados", summary = "Lista Pessoas") // usando o swagger para personalizar a documentação. Acesse http://localhost:8080/swagger-ui/index.html
    public List<Pessoa> getPessoas() {
        return pessoaService.listAll();
    }


    @GetMapping("/search") // esse metodo vai ser ativado se um GET for enviado para /pessoas/search
    public List<Pessoa> filtrarPeloNome(@RequestParam(required = false, name = "nomePessoa") String name, // parametro a ser passado na url
                                        @RequestParam(required = false) Integer idade,
                                        @RequestParam(required = false) List<Integer> ids) {

        FilterSearchParams searchParams = new FilterSearchParams(idade, name, ids);

        return pessoaService.filtrar(searchParams);
    }

    @PostMapping // Esse metodo vai ser ativado se um POST for enviado para /pessoas
    public void salvar(@RequestBody Pessoa pessoa // Pessoa vai vir do body da requisição e vai ser um json representando a pessoa.

    ) {
        pessoaService.save(pessoa);
    }

    @DeleteMapping("/{id}") // Esse metodo vai ser ativado se um DELETE for enviado para /pessoas/{id} onde {id} pode ser qualquer coisa
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteById(@PathVariable(name = "id") Integer idPessoa // @PathVariable pega a informação do id na url no caso em /pessoas/{id}
    ) {
        pessoaService.deleteById(idPessoa);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}") // Esse metodo vai ser ativado se um PUT for enviado para /pessoas/{id} onde {id} pode ser qualquer coisa
    @ResponseStatus(HttpStatus.OK) // responde com um ok, nesse caso redundante pois retornamos um ok manual no final do metodo
    public ResponseEntity editarPessoa(@RequestBody Pessoa pessoaNova, @PathVariable Integer id) {
        pessoaService.editPessoa(pessoaNova, id);
        return ResponseEntity.ok().build(); // resposta manual
    }

}
