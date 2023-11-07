package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Pessoa;
import br.com.targettrust.spring.aula.model.error.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PessoaService {

    private List<Pessoa> pessoas = new ArrayList<>();

    public PessoaService() {
        this.pessoas = new ArrayList<>(List.of(
                new Pessoa(1, "Maria", LocalDate.of(1990, 3,1)),
                new Pessoa(2, "Joao", LocalDate.of(1988, 4,1)),
                new Pessoa(3, "Antonio", LocalDate.of(1970, 6,10))
        ));
    }

    public void deleteById(Integer id) {
        Optional<Pessoa> pessoaLocalizada = this.pessoas.stream()
                .filter(pessoa -> Objects.equals(pessoa.getId(), id))
                .findFirst();

        if (pessoaLocalizada.isPresent()) {
            Pessoa pessoa = pessoaLocalizada.get();
            this.pessoas.remove(pessoa);
        }else {
            throw new NotFoundException("Pessoa", id.toString());
        }

    }

    public List<Pessoa> listAll() {
        return pessoas;
    }

    public void simularError() {
        throw new RuntimeException("error generico");

    }

    public void save(Pessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    public void editPessoa(Pessoa pessoaNova, Integer id) {
        Optional<Pessoa> pessoaLocalizada = this.pessoas.stream()
                .filter(pessoa -> Objects.equals(pessoa.getId(), id))
                .findFirst();

        if (pessoaLocalizada.isPresent()) {
            Pessoa pessoa = pessoaLocalizada.get();
            pessoaNova.setId(pessoa.getId());
            this.pessoas.remove(pessoa);
            this.pessoas.add(pessoaNova);

        } else {
            throw new NotFoundException("Pessoa", id.toString());
        }
    }
}
