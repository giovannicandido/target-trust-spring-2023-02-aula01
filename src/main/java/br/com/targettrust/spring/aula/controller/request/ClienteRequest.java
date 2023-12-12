package br.com.targettrust.spring.aula.controller.request;


import br.com.targettrust.spring.aula.model.cliente.Cliente;
import br.com.targettrust.spring.aula.model.pagamento.CartaoCredito;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class ClienteRequest {

    @NotBlank
    private String nome;

    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;

    @NotBlank
    @CPF
    private String cpf;

    @CreditCardNumber
    @NotBlank
    private String cartaoCredito;

    /**
     * Não vamos passar para dentro da aplicação (camadas internas) coisas da API (request e response)
     * Aqui transformamos os dados para passar para outras camadas.
     *
     * @return
     */
    public Cliente toModel() {
        return Cliente.builder()
            .nome(nome)
            .dataNascimento(dataNascimento)
            .cpf(cpf)
            .cartaoCredito(CartaoCredito.builder()
                .numero(cartaoCredito)
                .build())
            .build();
    }
}
