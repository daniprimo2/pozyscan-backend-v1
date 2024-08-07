package com.gerenciador.frota.aplicacao.gerenciador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_telefone", schema = "sc_gerenciador")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefone")
    private Long id;

    @Column(name = "numero_telefone")
    private String numero;

    @Column(name = "tipo_telefone")
    private String tipo;

    @JsonIgnore
    @ManyToOne
    private Contato contato_id;
}
