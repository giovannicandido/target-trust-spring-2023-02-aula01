package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.FilterSearchParams;
import br.com.targettrust.spring.aula.model.Pessoa;

import java.util.List;

/**
 * Operações do serviço de pessoa
 */
public interface PessoaService extends CommonCrudService<Pessoa, Integer> {

    void simularError();

    void save(Pessoa pessoa);

    void editPessoa(Pessoa pessoaNova, Integer id);

    List<Pessoa> filtrarPeloNome(String name);

    List<Pessoa> filtrar(FilterSearchParams params);
}
