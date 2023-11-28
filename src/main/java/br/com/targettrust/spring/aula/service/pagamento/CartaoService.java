package br.com.targettrust.spring.aula.service.pagamento;

import br.com.targettrust.spring.aula.model.pessoa.Pessoa;

public interface CartaoService {

    void salvarEvalidarCartao(String cartao, Pessoa pessoa);
}
