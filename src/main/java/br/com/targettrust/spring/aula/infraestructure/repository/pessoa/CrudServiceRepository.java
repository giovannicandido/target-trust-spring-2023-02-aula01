package br.com.targettrust.spring.aula.infraestructure.repository.pessoa;

import java.util.List;

/**
 * Essa interface serve para padronizar o que um service espera que um repository implemente para CRUD (Create, Retrieve, Update, Delete
 *
 * @param <T>
 * @param <ID>
 */
public interface CrudServiceRepository<T, ID> {

    void deleteById(ID id);

    List<T> listAll();

    void save(T entity);

    void edit(T entity, ID id);

    T findById(ID id);
}
