package br.com.targettrust.spring.aula.controller;

import br.com.targettrust.spring.aula.model.Pessoa;
import br.com.targettrust.spring.aula.model.error.NotFoundException;
import br.com.targettrust.spring.aula.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pessoas")
public class PessoaController {

    private final PessoaService pessoaService = new PessoaService();

    @GetMapping()
    public List<Pessoa> getPessoas() {
        return pessoaService.listAll();
    }

    @PostMapping
    public void salvar(@RequestBody Pessoa pessoa) {
        pessoaService.save(pessoa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteById(@PathVariable(name = "id") Integer idPessoa) {
        pessoaService.deleteById(idPessoa);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity editarPessoa(@RequestBody Pessoa pessoaNova, @PathVariable Integer id) {
        pessoaService.editPessoa(pessoaNova, id);
        return ResponseEntity.ok().build();
    }

}
