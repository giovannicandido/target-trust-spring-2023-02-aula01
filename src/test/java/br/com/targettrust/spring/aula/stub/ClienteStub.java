package br.com.targettrust.spring.aula.stub;

import br.com.targettrust.spring.aula.model.cliente.Cliente;
import br.com.targettrust.spring.aula.model.pagamento.CartaoCredito;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClienteStub {

    public static List<Cliente> createClientes(int quantity) {

        return IntStream.range(0, quantity)
            .mapToObj(index -> Cliente.builder()
                .id(index)
                .cpf("cpf " + index)
                .dataNascimento(LocalDate.now().minus(10, ChronoUnit.YEARS))
                .cartaoCredito(CartaoCredito.builder()
                    .id(Long.valueOf(index))
                    .numero("cartao " + index)
                    .build())
                .nome("nome " + index)
                .build())
            .collect(Collectors.toList());

    }
}
