package br.com.targettrust.spring.aula.controller.response;

import br.com.targettrust.spring.aula.model.Endereco;
import br.com.targettrust.spring.aula.model.Estado;
import lombok.*;

@Builder
@Data
public class EnderecoResponse {

    private String logradouro;
    private String bairro;
    private Integer numero;
    private String cidade;
    private Estado estado;
    private String cep;

    public static EnderecoResponse of(Endereco endereco) {
        return EnderecoResponse.builder()
                .cep(endereco.getCep())
                .bairro(endereco.getBairro())
                .estado(endereco.getEstado())
                .cidade(endereco.getCidade())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .build();
    }
}
