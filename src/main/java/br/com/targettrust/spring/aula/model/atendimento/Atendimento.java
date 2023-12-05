package br.com.targettrust.spring.aula.model.atendimento;

import br.com.targettrust.spring.aula.model.cliente.Cliente;
import br.com.targettrust.spring.aula.model.produto.Produto;
import br.com.targettrust.spring.aula.model.unidade.Unidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"unidade", "cliente", "produtos"})
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dataAtendimento;

    @Column(length = 80, nullable = false)
    private String nomeAtendente;

    private Boolean pagamentoEfetuado;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoAtendimento estado;

    @ManyToOne
    @JoinColumn(name = "unidade_id", foreignKey = @ForeignKey(name = "fk_atendimento_unidade"))
    private Unidade unidade;

    @ManyToOne
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_atendimento_cliente"))
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "atendimento_produtos",
        joinColumns = @JoinColumn(name = "atendimento_id", foreignKey = @ForeignKey(name = "fk_atendimento_produto_atendimento")),
        inverseJoinColumns = @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_atendimento_produto_produto"))
    )
    private List<Produto> produtos;

}
