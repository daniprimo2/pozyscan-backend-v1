package com.gerenciador.frota.aplicacao.rh.adapters.outound.entity;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TB_CARGO", schema = "sc_recursos_humanos")
public class CargoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CARGO")
    private Long id;
    @Column(name = "NOME_CARGO")
    private String nomeCargo;
    @Column(name = "DESC_CARGO")
    private String descricaoCargo;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CARGO")
    private TipoCargo tipoCargo;

    public CargoEntity(String nomeCargo, String descricaoCargo, TipoCargo tipoCargo) {
        this.nomeCargo = nomeCargo;
        this.descricaoCargo = descricaoCargo;
        this.tipoCargo = tipoCargo;
    }
}
