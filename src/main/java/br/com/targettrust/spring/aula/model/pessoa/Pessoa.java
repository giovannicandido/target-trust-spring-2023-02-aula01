package br.com.targettrust.spring.aula.model.pessoa;

import br.com.targettrust.spring.aula.model.pagamento.CartaoCredito;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Olhar os comentários de {@link Endereco}
 */
@Entity
@SequenceGenerator(name = "pessoa_seq", initialValue = 1, allocationSize = 1)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"enderecos", "cartaoCredito"})
public class Pessoa {

    @Id
    @GeneratedValue(generator = "pessoa_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 100)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<Endereco> enderecos;

    @OneToOne
    @JoinColumn(name = "cartao_credito_id", foreignKey = @ForeignKey(name = "fk_pessoa_cartao_credito"))
    private CartaoCredito cartaoCredito;
}
