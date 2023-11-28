package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.CartaoCredito;
import br.com.targettrust.spring.aula.model.Pessoa;
import br.com.targettrust.spring.aula.model.error.OutraPessoaPossuiEsseCartaoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartaoServiceImpl implements CartaoService {

    private final CartaoServiceRepository cartaoServiceRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void salvarEvalidarCartao(String numeroCartao, Pessoa pessoa) {
        log.info("Salvando cartao " + numeroCartao);

        boolean existeOutroDono = cartaoServiceRepository.existOutraPessoaComCartaoIgual(pessoa.getId(), numeroCartao);

        if (existeOutroDono) {
            throw new OutraPessoaPossuiEsseCartaoException();
        }

        CartaoCredito cartaoCredito = cartaoServiceRepository.findFirstByNumero(numeroCartao);

        if (cartaoCredito == null) {
            cartaoCredito = new CartaoCredito();
        }

        cartaoCredito.setNumero(numeroCartao);

        pessoa.setCartaoCredito(cartaoCredito);

        cartaoServiceRepository.save(cartaoCredito);

    }
}
