package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.EmailEmtity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    @NotBlank(message = "Deve ser informado o endereço do email.")
    private String email;
    private String observacoes;
    private TipoContato tipoContato;

    public EmailEmtity construirEmails(ColaboradorEntity colaboradorEntity) {
        return EmailEmtity.builder()
                .email(this.email)
                .observacoes(this.observacoes)
                .tipoContato(this.tipoContato)
                .colaboradorEntity(colaboradorEntity)
                .build();
    }
}
