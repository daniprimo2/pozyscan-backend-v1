package com.gerenciador.frota.aplicacao.logistica.utils.mappers;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaProdutoEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Produto;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ProdutoRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.RemessaResponse;

import java.util.List;

public class Mappers {

    public static JpaRemessaEntity fromRemessaToJpaRemessaEntity(Remessa remessa) {
        return  JpaRemessaEntity.builder()
                .id(remessa.getId())
                .statusRemessa(remessa.getStatusRemessa())
//                .jpaViagemEntity(fromViagemToJpaViagemEntity(remessa.getViagem()))
                .cliente(remessa.getCliente())
                .dataCriacao(remessa.getDataCriacao())
                .volumeTotal(remessa.getVolumeTotal())
                .pesoTotal(remessa.getPesoTotal())
                .build();
    }

    public static JpaViagemEntity fromViagemToJpaViagemEntity(Viagem viagem) {
        return JpaViagemEntity.builder()
                .id(viagem.getId())
                .dataProgramadaViagem(viagem.getDataProgramadaViagem())
                .dataRealizadoViagem(viagem.getDataRealizadoViagem())
                .tipoViagem(viagem.getTipoViagem())
                .dataCriacao(viagem.getDataCriacao())
                .pesoTotal(viagem.getPesoTotal())
                .veiculo(viagem.getVeiculo())
                .totalKilometragem(viagem.getTotalKilometragem())
                .totalRemessa(viagem.getTotalRemessa())
                .volumeTotal(viagem.getVolumeTotal())
                .build();
    }

    public static Remessa fromJpaRemessaEntityToRemessa(JpaRemessaEntity jpaRemessaEntitySalvo) {
        return new Remessa(
                jpaRemessaEntitySalvo.getId(),
                jpaRemessaEntitySalvo.getCliente(),
                jpaRemessaEntitySalvo.getDataCriacao(),
                jpaRemessaEntitySalvo.getVolumeTotal(),
                jpaRemessaEntitySalvo.getPesoTotal(),
                jpaRemessaEntitySalvo.getStatusRemessa());
    }

    public static RemessaResponse fromJpaRemessaEntityToRemessaResponse(JpaRemessaEntity jpaRemessaEntitySalvo) {
        return new RemessaResponse(
                jpaRemessaEntitySalvo.getId(),
                jpaRemessaEntitySalvo.getCliente(),
                jpaRemessaEntitySalvo.getDataCriacao(),
                jpaRemessaEntitySalvo.getVolumeTotal(),
                jpaRemessaEntitySalvo.getPesoTotal(),
                jpaRemessaEntitySalvo.getStatusRemessa());
    }

    public static List<NotaFiscalLogistica> fromListaJpaNotaFiscalToListaNotaFiscal(List<JpaNotaFiscalLogisticaEntity> jpaNotaFiscalLogisticaEntities) {
       return jpaNotaFiscalLogisticaEntities.stream().map(Mappers::fromNotaFiscalLogisticaToNotaFiscal).toList();
    }

    public static NotaFiscalLogistica fromNotaFiscalLogisticaToNotaFiscal(JpaNotaFiscalLogisticaEntity notaFiscalLogistica) {
        NotaFiscalLogistica nf = new NotaFiscalLogistica();
        nf.setCodigoNotaFiscal(notaFiscalLogistica.getCodigoNotaFiscal());
        nf.setNumeroNotaFisal(notaFiscalLogistica.getNumeroNotaFisal());
        nf.setValorTotal(notaFiscalLogistica.getValorTotal());
        nf.setDataEmissao(notaFiscalLogistica.getDataEmissao());
        nf.setRemessa(notaFiscalLogistica.getJpaRemessaEntity() == null ? null : fromJpaRemessaEntityToRemessa(notaFiscalLogistica.getJpaRemessaEntity()));
        nf.setEndereco(notaFiscalLogistica.getEndereco());
        return nf;
    }

    public static Viagem fromJpaViagemEntityToViagem(JpaViagemEntity jpaViagemEntity) {
        return new Viagem(
                jpaViagemEntity.getId(),
                jpaViagemEntity.getDataCriacao(),
                jpaViagemEntity.getDataProgramadaViagem(),
                jpaViagemEntity.getDataRealizadoViagem(),
                jpaViagemEntity.getVolumeTotal(),
                jpaViagemEntity.getPesoTotal(),
                jpaViagemEntity.getTotalKilometragem(),
                jpaViagemEntity.getTotalRemessa(),
                jpaViagemEntity.getVeiculo(),
                jpaViagemEntity.getTipoViagem(),
                jpaViagemEntity.getViagem().getRemessas());
    }

    public static JpaProdutoEntity fromProdutoRequestToProduto(ProdutoRequest request) {
        return new JpaProdutoEntity(
                null,
                request.getNomeProduto(),
                request.getDescricaoProduto(),
                request.getTipoProduto(),
                request.getPesoProdutoRequest().getPesoLiquido(),
                request.getPesoProdutoRequest().getPesoBruto(),
                request.getQuantidade(),
                request.getPrecoProdutoRequest().getValorLiquido(),
                request.getPrecoProdutoRequest().getValorBruto(),
                request.getDimensaoProdutoRequest().getLargura(),
                request.getDimensaoProdutoRequest().getAltura(),
                request.getDimensaoProdutoRequest().getComprimento(),
                null);
    }

    public static List<Viagem> fromListaJpaViagensToListaViagem(List<JpaViagemEntity> viagens) {
        return viagens.stream().map(Mappers::fromJpaViagemEntityToViagem).toList();
    }

    public static Produto fromJpaProdutoEntityToProduto(JpaProdutoEntity produtoSalvo) {
        return new Produto(
                null,
                produtoSalvo.getNomeProduto(),
                produtoSalvo.getDescricaoProduto(),
                produtoSalvo.getTipoProduto(),
                produtoSalvo.getPesoLiquido(),
                produtoSalvo.getPesoBruto(),
                produtoSalvo.getQuantidade(),
                produtoSalvo.getValorLiquido(),
                produtoSalvo.getValorBruto(),
                produtoSalvo.getLargura(),
                produtoSalvo.getAltura(),
                produtoSalvo.getComprimento(),
                null);
    }
    public static Produto fromComCodigoJpaProdutoEntityToProduto(JpaProdutoEntity produtoSalvo) {
        return new Produto(
                produtoSalvo.getCodigoProduto(),
                produtoSalvo.getNomeProduto(),
                produtoSalvo.getDescricaoProduto(),
                produtoSalvo.getTipoProduto(),
                produtoSalvo.getPesoLiquido(),
                produtoSalvo.getPesoBruto(),
                produtoSalvo.getQuantidade(),
                produtoSalvo.getValorLiquido(),
                produtoSalvo.getValorBruto(),
                produtoSalvo.getLargura(),
                produtoSalvo.getAltura(),
                produtoSalvo.getComprimento(),
                produtoSalvo.getJpaNotaFiscalLogisticaEntity() == null ? null :Mappers.fromNotaFiscalLogisticaToNotaFiscal(produtoSalvo.getJpaNotaFiscalLogisticaEntity()));
    }

    public static List<Produto> fromListJpaProdutoEntityToListProduto(List<JpaProdutoEntity> todosProdutos) throws NoSuchMethodException {
        try {
            return todosProdutos.stream().map(Mappers::fromJpaProdutoEntityToProduto).toList();
        } catch (Exception ex) {
            throw new NoSuchMethodException("Falhou ao mappear e converter Entity Produto na Entidade Produto.");
        }
    }

    public static JpaProdutoEntity fromProdutoRequestToProduto(Produto produto) {
        return new JpaProdutoEntity(
                null,
                produto.getNomeProduto(),
                produto.getDescricaoProduto(),
                produto.getTipoProduto(),
                produto.getPesoLiquido(),
                produto.getPesoBruto(),
                produto.getQuantidade(),
                produto.getValorLiquido(),
                produto.getValorBruto(),
                produto.getLargura(),
                produto.getAltura(),
                produto.getComprimento(),
                Mappers.fromNotaFiscalLogisticaToNotaFiscalEntity(produto.getNotaFiscalLogistica()));
    }

    private static JpaNotaFiscalLogisticaEntity fromNotaFiscalLogisticaToNotaFiscalEntity(NotaFiscalLogistica notaFiscalLogistica) {
        JpaNotaFiscalLogisticaEntity nf = new JpaNotaFiscalLogisticaEntity();
        nf.setCodigoNotaFiscal(notaFiscalLogistica.getCodigoNotaFiscal());
        nf.setNumeroNotaFisal(notaFiscalLogistica.getNumeroNotaFisal());
        nf.setValorTotal(notaFiscalLogistica.getValorTotal());
        nf.setDataEmissao(notaFiscalLogistica.getDataEmissao());
//        nf.setRemessa(fromJpaRemessaEntityToRemessa(notaFiscalLogistica.getJpaRemessaEntity()));
        nf.setEndereco(notaFiscalLogistica.getEndereco());
        return nf;
    }

    public static JpaProdutoEntity fromComCodigoProdutoRequestToProduto(Produto produto) {
        return new JpaProdutoEntity(
                produto.getCodigoProduto(),
                produto.getNomeProduto(),
                produto.getDescricaoProduto(),
                produto.getTipoProduto(),
                produto.getPesoLiquido(),
                produto.getPesoBruto(),
                produto.getQuantidade(),
                produto.getValorLiquido(),
                produto.getValorBruto(),
                produto.getLargura(),
                produto.getAltura(),
                produto.getComprimento(),
                produto.getNotaFiscalLogistica() == null ? null : Mappers.fromNotaFiscalLogisticaToNotaFiscalEntity(produto.getNotaFiscalLogistica()));
    }
}
