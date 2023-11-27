package br.com.targettrust.spring.aula.controller.request;

import br.com.targettrust.spring.aula.model.IniciarPagamento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.CreditCardNumber;

@Value
public class PagamentoRequest {

    @NotBlank
    private final String cep;

    @Email
    @NotBlank
    private final String email;

    @CreditCardNumber
    @NotBlank
    private String numeroCartao;

    @NotNull
    private Integer numero;

    public IniciarPagamento toModel() {
        return IniciarPagamento.builder()
                .cep(cep)
                .email(email)
                .numeroCartao(numeroCartao)
                .numero(numero)
                .build();
    }
}
