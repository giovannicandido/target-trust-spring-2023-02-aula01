package br.com.targettrust.spring.aula.infraestructure.repository;

import br.com.targettrust.spring.aula.model.animal.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    @Query("select p from Cliente c join c.pets p where c.id = :clientId")
    List<Pet> findAllByClienteId(@Param("clientId") Integer clientId);
}
