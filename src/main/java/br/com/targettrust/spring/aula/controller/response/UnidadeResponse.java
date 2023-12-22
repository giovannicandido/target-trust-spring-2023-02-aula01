package br.com.targettrust.spring.aula.controller.response;

import br.com.targettrust.spring.aula.model.unidade.Unidade;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class UnidadeResponse {

    private Integer id;

    private String nome;

    private EnderecoResponse endereco;

    public static UnidadeResponse of(Unidade unidade) {
        return UnidadeResponse.builder()
            .id(unidade.getId())
            .nome(unidade.getNome())
            .endereco(EnderecoResponse.of(unidade.getEndereco()))
            .build();
    }

    public static Page<UnidadeResponse> of(Page<Unidade> unidades) {
        return unidades.map(UnidadeResponse::of);
    }
}
