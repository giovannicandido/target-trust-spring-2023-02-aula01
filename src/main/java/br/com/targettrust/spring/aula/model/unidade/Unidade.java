package br.com.targettrust.spring.aula.model.unidade;

import br.com.targettrust.spring.aula.model.cliente.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "endereco")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false, foreignKey = @ForeignKey(name = "fk_unidade_endereco"))
    private Endereco endereco;


}
