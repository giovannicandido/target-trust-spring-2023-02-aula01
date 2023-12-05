package br.com.targettrust.spring.aula.model.cliente;

import br.com.targettrust.spring.aula.model.animal.Animal;
import br.com.targettrust.spring.aula.model.pagamento.CartaoCredito;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class Cliente {

    @Id
    @GeneratedValue(generator = "pessoa_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 100)
    private String nome;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
    private List<Endereco> enderecos;

    @OneToOne
    @JoinColumn(name = "cartao_credito_id", foreignKey = @ForeignKey(name = "fk_pessoa_cartao_credito"))
    private CartaoCredito cartaoCredito;

    @OneToMany
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cliente_animal"))
    private List<Animal> animais;
}
