package br.com.targettrust.spring.aula.service.pagamento;

import br.com.targettrust.spring.aula.infraestructure.repository.pessoa.CrudServiceRepository;
import br.com.targettrust.spring.aula.model.pagamento.CartaoCredito;

public interface CartaoServiceRepository extends CrudServiceRepository<CartaoCredito, Long> {

    CartaoCredito findFirstByNumero(String numero);

    boolean existOutraPessoaComCartaoIgual(Integer id, String numeroCartao);
}
