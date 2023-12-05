package br.com.targettrust.spring.aula.controller.request;

import br.com.targettrust.spring.aula.model.unidade.Unidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnidadeRequest {
    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotNull
    @Valid
    private EnderecoRequest endereco;

    public Unidade toModel() {
        return Unidade.builder()
            .nome(nome)
            .endereco(endereco.toModel())
            .build();
    }
}
