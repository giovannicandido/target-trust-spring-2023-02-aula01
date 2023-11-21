package br.com.targettrust.spring.aula.repository;

import java.util.List;

public interface CrudServiceRepository<T, ID> {

    void deleteById(ID id);

    List<T> listAll();

    void save(T entity);

    void edit(T entity, ID id);

    T findById(ID id);
}
