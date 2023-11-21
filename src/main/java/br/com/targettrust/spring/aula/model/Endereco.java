package br.com.targettrust.spring.aula.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@SequenceGenerator(name = "endereco_generator", sequenceName = "endereco_sequence")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "pessoa")
public class Endereco {
    @Id
    @GeneratedValue(generator = "endereco_generator" ,strategy = GenerationType.SEQUENCE)
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
    private Estado estado;

    @Column(length = 16)
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", foreignKey = @ForeignKey(name = "fk_endereco_pessoa"), nullable = false)
    private Pessoa pessoa;
}
