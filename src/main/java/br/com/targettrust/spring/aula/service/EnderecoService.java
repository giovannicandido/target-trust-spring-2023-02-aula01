package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Endereco;

import java.util.List;

public interface EnderecoService {

    List<Endereco> listAll();

    void save(Endereco endereco, Integer pessoaId);
}
