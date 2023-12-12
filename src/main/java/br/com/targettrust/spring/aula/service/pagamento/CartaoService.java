package br.com.targettrust.spring.aula.service.pagamento;

import br.com.targettrust.spring.aula.model.cliente.Cliente;

public interface CartaoService {

    void salvarEvalidarCartao(String cartao, Cliente cliente);
}
