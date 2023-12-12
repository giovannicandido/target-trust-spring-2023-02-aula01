package br.com.targettrust.spring.aula.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TipoAnimalRequest {

    @NotNull
    private TipoAnimalRequestEnum tipo;

    @NotNull
    @Valid
    private InformacoesRequest informacoes;

    @NotBlank
    private String especie;
}
