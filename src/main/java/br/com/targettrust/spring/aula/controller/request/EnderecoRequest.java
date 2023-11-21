package br.com.targettrust.spring.aula.controller.request;

import br.com.targettrust.spring.aula.model.Endereco;
import br.com.targettrust.spring.aula.model.Estado;
import jakarta.validation.constraints.*;
import lombok.*;


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
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        return endereco;
    }
}
