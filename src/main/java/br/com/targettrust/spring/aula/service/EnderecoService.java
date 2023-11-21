package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Endereco;

public interface EnderecoService extends CommonCrudService<Endereco, Long> {
    /**
     * Notar que o save não está na CommonCrudService porque aqui salvamos um pouco diferente,
     * Precisamos do id da pessoa para salvar o endereço que tem que apontar para pessoa obrigatoriamente. Nossa modelagem não existe Endereço sem pessoa
     *
     * @param endereco
     * @param pessoaId
     */
    void save(Endereco endereco, Integer pessoaId);
}
