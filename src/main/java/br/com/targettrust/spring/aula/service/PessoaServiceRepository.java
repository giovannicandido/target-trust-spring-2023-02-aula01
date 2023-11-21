package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Pessoa;
import br.com.targettrust.spring.aula.repository.CrudServiceRepository;

/**
 * Operações que fizemos em pessoa
 * Esse é o contrato entre camadas, evite usar implementações diretamente entre camadas.
 */
public interface PessoaServiceRepository extends CrudServiceRepository<Pessoa, Integer> {

}
