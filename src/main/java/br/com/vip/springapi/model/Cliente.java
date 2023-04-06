package br.com.vip.springapi.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_cliente")
@SequenceGenerator(name = "seq_cliente", allocationSize = 1)
@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(generator = "seq_cliente", strategy =  GenerationType.SEQUENCE)
    @Column(name = "cl_id")
    private Long id;

    @Column(name = "cl_nome", nullable = false)
    private String nome;

    @Embedded
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cl_usuario_id", nullable = false)
    private Usuario usuario;
}
