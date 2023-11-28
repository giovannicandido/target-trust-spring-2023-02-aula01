package br.com.targettrust.spring.aula.model.pessoa;

import lombok.Value;

import java.util.Collections;
import java.util.List;

/**
 * Esta classe representa os parâmetros para filtrar os resultados da pesquisa.
 */
@Value
public class PessoaSearchParams {
    private final Integer idade;
    private final String nome;
    private final List<Integer> ids;

    /**
     * Se for criado um construtor essa vai sobrescrever o do lombok @Value cria um construtor com todos os itens
     *
     * @param idade
     * @param nome
     * @param ids
     */
    public PessoaSearchParams(Integer idade, String nome, List<Integer> ids) {
        this.idade = idade;
        this.nome = nome;
        this.ids = ids == null ? Collections.emptyList() : ids;
    }
}
