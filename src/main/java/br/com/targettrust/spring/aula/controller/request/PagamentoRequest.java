package br.com.targettrust.spring.aula.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
}
