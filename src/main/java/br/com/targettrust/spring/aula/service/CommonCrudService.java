package br.com.targettrust.spring.aula.service;

import java.util.List;

public interface CommonCrudService<T, ID> {
    List<T> listAll();

    void deleteById(ID id);
}
