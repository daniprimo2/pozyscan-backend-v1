package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CargoRequest {

    @NotBlank(message = "O nome cargo e obrigatorio")
    private String nomeCargo;
    @NotBlank(message = "A descrição e obrigatorio")
    @Size(max = 255, message = "A descrição não pode ultrapassar de 255 caracteres")
    private String descricaoCargo;
    private TipoCargo tipoCargo;

    public CargoEntity construirCargo() {
        return CargoEntity.builder()
                .nomeCargo(this.nomeCargo)
                .descricaoCargo(this.descricaoCargo)
                .tipoCargo(tipoCargo)
                .build();
    }

    public CargoEntity atualizandoCargo(Long id) {
        return CargoEntity.builder()
                .id(id)
                .nomeCargo(this.nomeCargo)
                .descricaoCargo(this.descricaoCargo)
                .tipoCargo(tipoCargo)
                .build();
    }
}
