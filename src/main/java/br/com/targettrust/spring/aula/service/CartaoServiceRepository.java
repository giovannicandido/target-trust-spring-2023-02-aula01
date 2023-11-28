package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.CartaoCredito;
import br.com.targettrust.spring.aula.repository.CrudServiceRepository;

public interface CartaoServiceRepository extends CrudServiceRepository<CartaoCredito, Long> {

    CartaoCredito findFirstByNumero(String numero);

    boolean existOutraPessoaComCartaoIgual(Integer id, String numeroCartao);
}
