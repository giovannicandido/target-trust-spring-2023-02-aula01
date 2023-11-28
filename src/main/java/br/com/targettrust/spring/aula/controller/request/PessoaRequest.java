package br.com.targettrust.spring.aula.controller.request;


import br.com.targettrust.spring.aula.model.pessoa.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaRequest {

    @NotBlank
    private String nome;

    @NotNull
    @PastOrPresent
    private LocalDate dataNascimento;

    /**
     * Não vamos passar para dentro da aplicação (camadas internas) coisas da API (request e response)
     * Aqui transformamos os dados para passar para outras camadas.
     *
     * @return
     */
    public Pessoa toModel() {
        return Pessoa.builder()
                .nome(nome)
                .dataNascimento(dataNascimento)
                .build();
    }
}
