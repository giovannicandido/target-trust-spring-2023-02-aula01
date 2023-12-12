package br.com.targettrust.spring.aula.controller.request;

import br.com.targettrust.spring.aula.model.animal.TipoAgua;
import lombok.Data;

@Data
public class InformacoesRequest {
    private Boolean peconhento;

    private TipoAgua tipoAgua;

    private String raca;

    private Boolean possuiPelos;

}
