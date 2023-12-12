package br.com.targettrust.spring.aula.controller.response;


import br.com.targettrust.spring.aula.model.cliente.Cliente;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Veja comentários em {@link EnderecoResponse} o mesmo se aplica aqui
 */
@Data
@Builder
public class ClienteResponse {
    private Integer id;

    private String nome;

    private LocalDate dataNascimento;

    private List<EnderecoResponse> enderecos;

    public static ClienteResponse of(Cliente cliente) {
        return commonBuild(cliente).build();
    }

    public static ClienteResponseBuilder commonBuild(Cliente cliente) {
        return ClienteResponse.builder()
            .id(cliente.getId())
            .nome(cliente.getNome())
            .dataNascimento(cliente.getDataNascimento());
    }

    public static ClienteResponse ofDetails(Cliente cliente) {
        return commonBuild(cliente)
            .enderecos(
                Objects.isNull(cliente.getEnderecos()) ? null : cliente.getEnderecos().stream().map(EnderecoResponse::of).toList()
            )
            .build();
    }

}
