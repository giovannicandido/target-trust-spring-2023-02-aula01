package br.com.targettrust.spring.aula.service;

import br.com.targettrust.spring.aula.model.Pessoa;

import java.util.List;

public interface CrudServiceRepository<T, ID> {

    void deleteById(ID id);

    List<T> listAll();

    void save(T entity);

    void edit(T entity, ID id);

    T findById(ID id);
}
