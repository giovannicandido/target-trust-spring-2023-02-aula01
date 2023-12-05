package br.com.targettrust.spring.aula.controller.response;


import br.com.targettrust.spring.aula.model.cliente.Cliente;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * Veja comentários em {@link EnderecoResponse} o mesmo se aplica aqui
 */
@Data
@Builder
public class PessoaResponse {
    private Integer id;

    private String nome;

    private LocalDate dataNascimento;

    public static PessoaResponse of(Cliente pessoa) {
        return PessoaResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(pessoa.getDataNascimento())
                .build();
    }
}
