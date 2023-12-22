package br.com.targettrust.spring.aula.service.cliente;

import br.com.targettrust.spring.aula.model.cliente.Endereco;
import br.com.targettrust.spring.aula.service.CommonCrudService;

import java.util.List;

public interface EnderecoService extends CommonCrudService<Endereco, Long> {
    /**
     * Notar que o save não está na CommonCrudService porque aqui salvamos um pouco diferente,
     * Precisamos do id da cliente para salvar o endereço que tem que apontar para cliente obrigatoriamente. Nossa modelagem não existe Endereço sem cliente
     *
     * @param endereco
     */
    void save(Endereco endereco, Integer idCliente);

    List<Endereco> searchByRua(String rua);

    List<Endereco> searchByNomeCliente(String nomeCliente);
}
