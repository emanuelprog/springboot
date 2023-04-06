package br.com.vip.springapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "seq_usuario", allocationSize = 1)
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(generator = "seq_usuario", strategy = GenerationType.SEQUENCE)
    @Column(name = "us_id")
    private Long id;

    @Column(name = "us_email", unique = true, nullable = false)
    private String email;

    @Column(name = "us_password", nullable = false)
    private String password;
    
    @Column(name = "us_ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "us_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelAcesso role;
}
