package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Endereco;

public interface EnderecoService extends CommonCrudService<Endereco, Long> {

    void save(Endereco endereco, Integer pessoaId);
}
