package br.com.targettrust.spring.aula.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class IniciarPagamento {

    private final String cep;

    private final String email;

    private String numeroCartao;

    private final Integer numero;
}
