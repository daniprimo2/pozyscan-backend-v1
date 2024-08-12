package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.CategoriaRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.TipoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Categoria;
import com.gerenciador.frota.aplicacao.gerenciador.model.Tipo;
import com.gerenciador.frota.aplicacao.gerenciador.model.Veiculo;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoRequest {

    private String placa;
    private String modelo;
    private String ano;
    private Long categoria_id;
    private Long tipo_id;


    public Veiculo construirVeiculo(VeiculoRequest veiculoRequest,
                                    CategoriaRepository categoriaRepository,
                                    TipoRepository tipoRepository) {
        return Veiculo.builder()
                .placa(veiculoRequest.getPlaca())
                .modelo(veiculoRequest.modelo)
                .ano(veiculoRequest.getAno())
                .categoria_id(categoriaRepository.getById(categoria_id))
                .tipo_id(tipoRepository.getById(tipo_id))
                .build();
    }
}
