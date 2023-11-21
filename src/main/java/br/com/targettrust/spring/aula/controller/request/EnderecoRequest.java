package br.com.targettrust.spring.aula.controller.request;

import br.com.targettrust.spring.aula.model.Endereco;
import br.com.targettrust.spring.aula.model.Estado;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EnderecoRequest {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotNull
    @Min(1)
    @Max(10000)
    private Integer numero;

    @NotBlank
    private String cidade;

    @NotNull
    private Estado estado;

    private String cep;

    @NotNull
    private Integer pessoaId;

    public Endereco toModel() {
        return Endereco.builder()
                .cep(cep)
                .bairro(bairro)
                .cidade(cidade)
                .estado(estado)
                .logradouro(logradouro)
                .numero(numero)
                .build();
    }
}
