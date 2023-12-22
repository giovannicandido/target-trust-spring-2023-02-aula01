package br.com.targettrust.spring.aula.model.pessoa;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa uma entidade do JPA, ou seja um mapeamento entre tabela e classe
 */
@Entity
// obrigatorio, por padrão o nome da classe será o nome da tabela (Use @Table(name = "personalizado") para trocar)
@SequenceGenerator(name = "endereco_generator", sequenceName = "endereco_sequence") // Declara uma sequence no banco
@Builder // Usando o padrão builder do lombok
@NoArgsConstructor // Construtor padrão que toda entidade precisa ter (construtor vazio)
@AllArgsConstructor // o builder precisa desse
@Getter // Não use @Data ou @Value com entidades
@Setter
@ToString(exclude = "pessoa")
// Ao criar o ToString automatico com o lombok tenha cuidado com relações, exclua elas pois senão o JPA vai carregar dados do banco de dados quando imprimir a entidade
public class Endereco {
    @Id // obrigatório, toda entidade precisa de um id
    @GeneratedValue(generator = "endereco_generator", strategy = GenerationType.SEQUENCE) // usa a sequence declarada
    private Long id;

    @Column(name = "rua", length = 40, nullable = false, unique = true)
    private String logradouro;

    @Column(length = 40, nullable = false)
    private String bairro;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false, length = 50)
    private String cidade;

    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    // eu sempre uso pois por padrão todos os Enum virão numeros e isso depende da ordem do ENUM, aqui vai ser uma String representando o valor do ENUM
    private Estado estado;

    @Column(length = 16)
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", foreignKey = @ForeignKey(name = "fk_endereco_pessoa"), nullable = false)
    private Pessoa pessoa;
}
