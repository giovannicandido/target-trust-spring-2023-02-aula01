package br.com.targettrust.spring.aula;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pessoas")
public class PessoaController {

    private List<Pessoa> pessoas = new ArrayList<>();

    public PessoaController(List<Pessoa> pessoas) {
        this.pessoas = new ArrayList<>(List.of(
                new Pessoa(1, "Maria", LocalDate.of(1990, 3,1)),
                new Pessoa(2, "Joao", LocalDate.of(1988, 4,1)),
                new Pessoa(3, "Antonio", LocalDate.of(1970, 6,10))
        ));
    }

    @GetMapping()
    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    @PostMapping
    public void salvar(@RequestBody Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(name = "id") Integer idPessoa) {
        Optional<Pessoa> pessoaLocalizada = this.pessoas.stream()
                .filter(pessoa -> pessoa.getId() == idPessoa)
                .findFirst();

        if (pessoaLocalizada.isPresent()) {
            Pessoa pessoa = pessoaLocalizada.get();
            this.pessoas.remove(pessoa);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity editarPessoa(@RequestBody Pessoa pessoaNova, @PathVariable Integer id) {
        Optional<Pessoa> pessoaLocalizada = this.pessoas.stream()
                .filter(pessoa -> pessoa.getId() == id)
                .findFirst();

        if (pessoaLocalizada.isPresent()) {
            Pessoa pessoa = pessoaLocalizada.get();
            pessoaNova.setId(pessoa.getId());
            this.pessoas.remove(pessoa);
            this.pessoas.add(pessoaNova);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
