package com.gerenciador.frota.aplicacao.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_email", schema = "sc_gerenciador")
public class EmailContato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_email")
    private Long id;

    @Email
    @Column(name = "endereco_email", nullable = false)
    private String email;

    @Column(name = "tipo_email")
    private String tipo;

    @JsonIgnore
    @ManyToOne
    private Contato contato_id;

}
