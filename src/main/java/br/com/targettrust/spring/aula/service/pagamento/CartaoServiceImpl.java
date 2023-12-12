package br.com.targettrust.spring.aula.service.pagamento;

import br.com.targettrust.spring.aula.infraestructure.repository.pagamento.CartaoRepository;
import br.com.targettrust.spring.aula.model.cliente.Cliente;
import br.com.targettrust.spring.aula.model.error.OutraClientePossuiEsseCartaoException;
import br.com.targettrust.spring.aula.model.pagamento.CartaoCredito;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartaoServiceImpl implements CartaoService {

    private final CartaoRepository cartaoServiceRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void salvarEvalidarCartao(String numeroCartao, Cliente cliente) {
        log.info("Salvando cartao " + numeroCartao);

        boolean existeOutroDono = cartaoServiceRepository.existsByNumeroAndClienteIdNotIn(numeroCartao, cliente.getId());

        if (existeOutroDono) {
            throw new OutraClientePossuiEsseCartaoException();
        }

        CartaoCredito cartaoCredito = cartaoServiceRepository.findFirstByNumero(numeroCartao);

        if (cartaoCredito == null) {
            cartaoCredito = new CartaoCredito();
        }

        cartaoCredito.setNumero(numeroCartao);

        cliente.setCartaoCredito(cartaoCredito);

        cartaoServiceRepository.save(cartaoCredito);

    }
}
