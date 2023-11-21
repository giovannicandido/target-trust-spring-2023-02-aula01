package br.com.targettrust.spring.aula.controller.response;


import br.com.targettrust.spring.aula.model.Pessoa;
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

    public static PessoaResponse of(Pessoa pessoa) {
        return PessoaResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(pessoa.getDataNascimento())
                .build();
    }
}
