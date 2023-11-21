package br.com.targettrust.spring.aula.service;

import java.util.List;

/**
 * A common CRUD (Create, Read, Update, Delete) service interface for managing entities implemented by all services.
 *
 * @param <T>  The type of entity that this service will handle.
 * @param <ID> The type of identifier for the entities.
 */
public interface CommonCrudService<T, ID> {
    List<T> listAll();

    void deleteById(ID id);
}
