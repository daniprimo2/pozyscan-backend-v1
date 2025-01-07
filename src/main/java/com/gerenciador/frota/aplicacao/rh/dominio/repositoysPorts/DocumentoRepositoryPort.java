package com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DocumentosEntity;

public interface DocumentoRepositoryPort {

    DocumentosEntity buscarDocumentoPorTipo(Long codigoDocumento, TipoDocumento tipoDocumento);
    DocumentosEntity salvar(DocumentosEntity documentosEntity);

    DocumentosEntity atualizarDocumento(Long codigoDocumento, DocumentosEntity documentosEntity);
}
