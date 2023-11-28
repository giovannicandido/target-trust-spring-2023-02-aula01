package br.com.targettrust.spring.aula.service.pagamento;

import br.com.targettrust.spring.aula.model.pagamento.CartaoCredito;
import br.com.targettrust.spring.aula.repository.pessoa.CrudServiceRepository;

public interface CartaoServiceRepository extends CrudServiceRepository<CartaoCredito, Long> {

    CartaoCredito findFirstByNumero(String numero);

    boolean existOutraPessoaComCartaoIgual(Integer id, String numeroCartao);
}
