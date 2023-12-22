package br.com.targettrust.spring.aula.controller.request;

import br.com.targettrust.spring.aula.model.cliente.Endereco;
import br.com.targettrust.spring.aula.model.cliente.Estado;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Essa é uma classe de dados feita apenas para conter informações ou representar uma informação
 * Por isso é comum que se use @Data ou @Value (imutável)
 */
@Data
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
    private Integer clienteId;

    /**
     * Não vamos passar para dentro da aplicação (camadas internas) coisas da API (request e response)
     * Aqui transformamos os dados para passar para outras camadas.
     *
     * @return
     */
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
