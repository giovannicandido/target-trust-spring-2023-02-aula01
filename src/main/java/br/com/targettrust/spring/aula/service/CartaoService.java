package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Pessoa;

public interface CartaoService {

    void salvarEvalidarCartao(String cartao, Pessoa pessoa);
}
