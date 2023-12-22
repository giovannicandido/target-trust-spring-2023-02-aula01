package br.com.targettrust.spring.aula.service.pagamento;

import br.com.targettrust.spring.aula.infraestructure.repository.pagamento.CartaoRepository;
import br.com.targettrust.spring.aula.model.cliente.Cliente;
import br.com.targettrust.spring.aula.model.error.OutroClientePossuiEsseCartaoException;
import br.com.targettrust.spring.aula.model.pagamento.CartaoCredito;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CartaoServiceImplTest {

    private CartaoRepository cartaoRepository = Mockito.mock(CartaoRepository.class);

    @Test
    void given_CartaoExisteParaOutroCliente_when_salvarEValidarCartao_should_throwOutroClientePossuiEsseCartaoException() {

        CartaoServiceImpl cartaoService = new CartaoServiceImpl(cartaoRepository);

        Mockito.doReturn(true)
            .when(cartaoRepository).existsByNumeroAndClienteIdNotIn("1234-456", 1);

        Cliente cliente = new Cliente();

        cliente.setId(1);

        Assertions.assertThrows(OutroClientePossuiEsseCartaoException.class, () -> cartaoService.salvarEvalidarCartao("1234-456", cliente));
    }

    @Test
    void given_clienteEvalido_when_salvarEValidarCartao_should_saveInDatabase() {

        CartaoServiceImpl cartaoService = new CartaoServiceImpl(cartaoRepository);

        Mockito.doReturn(false)
            .when(cartaoRepository).existsByNumeroAndClienteIdNotIn("1234-456", 1);

        CartaoCredito cartaoCredito = new CartaoCredito();

        cartaoCredito.setNumero("1234-456");

        Mockito.doReturn(cartaoCredito)
            .when(cartaoRepository).findFirstByNumero("1234-456");


        Cliente cliente = new Cliente();

        cliente.setId(1);

        cartaoService.salvarEvalidarCartao("1234-456", cliente);

        Mockito.verify(cartaoRepository).save(cartaoCredito);

    }


}