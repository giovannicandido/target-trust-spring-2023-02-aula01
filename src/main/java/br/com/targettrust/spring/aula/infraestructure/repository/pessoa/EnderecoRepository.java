package br.com.targettrust.spring.aula.infraestructure.repository.pessoa;

import br.com.targettrust.spring.aula.model.cliente.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Toda interface que extende JpaRepository virará um repository do spring data
 * De modo que não precisa de implementação e fornecerá acesso aos dados daquela entidade (tabela)
 * É sempre feito um repository por entidade
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long> { // tipo entidade, tipo id entidade

    @Query(value = "select * from endereco where endereco.rua ilike :rua", nativeQuery = true)
    List<Endereco> findEnderecoByLogradouro(@Param("rua") String logradouro);

    @Query(value = "select e from Endereco e where e.logradouro ilike :rua")
    List<Endereco> findEnderecoByLogradouroJPA(@Param("rua") String logradouro);

    @Query(value = "select e from Endereco e join e.pessoa p where p.nome ilike :nomePessoa")
    List<Endereco> findEnderecoByPessoaNome(String nomePessoa);

    /**
     * Producura usando os methods magicos do spring data ( o metodo vira a query: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html )
     *
     * @param logradouro
     * @return
     */
    List<Endereco> findAllByLogradouroIsLike(String logradouro);

    /**
     * Producura usando os methods magicos do spring data ( o metodo vira a query: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html )
     *
     * @param logradouro
     * @return
     */

    @Query("select e.logradouro as logradouro, e.numero as numero from Endereco e where e.logradouro ilike :logradouro")
    List<EnderecoProjection> findAllByLogradouroIsLikeWithProjection(String logradouro);


    @Modifying
        // sinaliza que é um update ou delete
    void deleteAllByBairro(String bairro);

    @Query("delete from Endereco e  where e.bairro = :bairro")
    @Modifying
        // se a query modifica tem que ter essa anotação
    void deletePersonalizado(String bairro);




}
