package br.com.targettrust.spring.aula.controller.response;

import br.com.targettrust.spring.aula.model.Endereco;
import br.com.targettrust.spring.aula.model.Estado;
import lombok.Builder;
import lombok.Data;

@Builder
@Data // Bem comum usar o padrão builder veja o metodo Get do controlador de endereço que vai criar essa response
public class EnderecoResponse {

    private String logradouro;
    private String bairro;
    private Integer numero;
    private String cidade;
    private Estado estado;
    private String cep;

    /**
     * Vamos transformar um model/dominio para sua resposta de API usando esse método
     *
     * @param endereco Model
     * @return Response
     */
    public static EnderecoResponse of(Endereco endereco) {
        return EnderecoResponse.builder()
                .cep(endereco.getCep())
                .bairro(endereco.getBairro())
                .estado(endereco.getEstado())
                .cidade(endereco.getCidade())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .build();
    }
}
