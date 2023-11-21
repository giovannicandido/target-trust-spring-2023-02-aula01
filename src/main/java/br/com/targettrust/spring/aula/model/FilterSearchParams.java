package br.com.targettrust.spring.aula.model;

import lombok.Value;

import java.util.Collections;
import java.util.List;

/**
 * Esta classe representa os parâmetros para filtrar os resultados da pesquisa.
 */
@Value
public class FilterSearchParams {
    private final Integer idade;
    private final String nome;
    private final List<Integer> ids;

    public FilterSearchParams(Integer idade, String nome, List<Integer> ids) {
        this.idade = idade;
        this.nome = nome;
        this.ids = ids == null ? Collections.emptyList() : ids;
    }
}
