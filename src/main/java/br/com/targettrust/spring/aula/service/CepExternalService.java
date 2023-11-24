package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Endereco;

import java.util.Optional;

public interface CepExternalService {

    Optional<Endereco> searchEnderecoByCep(String cep);

}
