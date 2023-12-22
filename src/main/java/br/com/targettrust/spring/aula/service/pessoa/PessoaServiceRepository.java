package br.com.targettrust.spring.aula.service.pessoa;

import br.com.targettrust.spring.aula.infraestructure.repository.pessoa.CrudServiceRepository;
import br.com.targettrust.spring.aula.model.pessoa.Pessoa;

/**
 * Operações que fizemos em pessoa
 * Esse é o contrato entre camadas, evite usar implementações diretamente entre camadas.
 */
public interface PessoaServiceRepository extends CrudServiceRepository<Pessoa, Integer> {

}
