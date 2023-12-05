package br.com.targettrust.spring.aula.model.produto;

import br.com.targettrust.spring.aula.model.animal.Animal;
import br.com.targettrust.spring.aula.model.atendimento.Atendimento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"animal", "atendimentos"})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_produto_animal"))
    private Animal animal;

    @ManyToMany(mappedBy = "produtos")
    private List<Atendimento> atendimentos;
}
