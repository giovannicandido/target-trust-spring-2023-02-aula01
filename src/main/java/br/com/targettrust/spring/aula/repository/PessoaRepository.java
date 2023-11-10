package br.com.targettrust.spring.aula.repository;

import br.com.targettrust.spring.aula.model.Pessoa;

import java.util.List;

/**
 * Operações que fizemos em pessoa
 * Esse é o contrato entre camadas, evite usar implementações diretamente entre camadas.
 */
public interface PessoaRepository {
    void deleteById(Integer id);

    List<Pessoa> listAll();

    void save(Pessoa pessoa);

    void editPessoa(Pessoa pessoaNova, Integer id);
}
