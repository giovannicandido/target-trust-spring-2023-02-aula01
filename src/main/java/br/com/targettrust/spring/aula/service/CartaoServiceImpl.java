package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Pessoa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CartaoServiceImpl implements CartaoService {
    @Override
    public void salvarEvalidarCartao(String cartao, Pessoa pessoa) {
        log.info("Salvando cartao " + cartao);
    }
}
