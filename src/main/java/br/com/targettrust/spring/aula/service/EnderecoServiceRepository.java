package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Endereco;
import br.com.targettrust.spring.aula.repository.CrudServiceRepository;

/**
 * Essa classe está em service pois ela é o que o service de Endereco espera que um repository implemente, a logica aqui é inversa
 * Nosso service não deveria depender de uma tecnologia explicitamente, então ele depende apenas de uma interface
 * Nosso repository na camada de repository que vai usar a tecnologia do spring para implementar esse metodos.
 * Se trocarmos a tecnologia de banco, ou não usarmos o banco de dados mais basta implementar essa interface novamente e trocar a injeção
 * O restante do codigo permanece o mesmo.
 * <p>
 * Algumas arquiteturas pregam esse tipo de separação (clean architecture) outras não são explicitas quanto a isso.
 * <p>
 * Não é estritamente necessário para uma introdução ao spring mas é uma pratica comum em projetos
 * (Não a interface de crud em si, eu inventei por ser comum)
 * <p>
 * The EnderecoServiceRepository interface extends the CrudServiceRepository interface for managing Endereco entities.
 * <p>
 * This interface provides the methods for CRUD (Create, Read, Update, Delete) operations on Endereco entities.
 * <p>
 * It defines the following methods:
 * <p>
 * - create: Creates a new Endereco entity.
 * - findById: Finds an Endereco entity by its ID.
 * - update: Updates an existing Endereco entity.
 * - delete: Deletes an Endereco entity.
 *
 * @param <Endereco> The type of the entity.
 * @param <Long>     The type of the ID of the entity.
 */
public interface EnderecoServiceRepository extends CrudServiceRepository<Endereco, Long> {
}
