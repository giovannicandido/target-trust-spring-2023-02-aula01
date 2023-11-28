package br.com.targettrust.spring.aula.service.external;

import br.com.targettrust.spring.aula.model.pessoa.Endereco;

import java.util.Optional;

public interface CepExternalService {

    Optional<Endereco> searchEnderecoByCep(String cep);

}
