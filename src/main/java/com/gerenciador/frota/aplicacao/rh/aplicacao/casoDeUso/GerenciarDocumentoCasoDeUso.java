package com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DocumentosEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.DocumentoRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class GerenciarDocumentoCasoDeUso {

    private final DocumentoRepositoryPort documentoRepositoryPort;

    public DocumentosEntity buscarDocumentoPorTipo(Long codigoDocumento, TipoDocumento tipoDocumento) {
        return documentoRepositoryPort.buscarDocumentoPorTipo(codigoDocumento, tipoDocumento);
    }

    public DocumentosEntity salvarDocumento(DocumentosEntity documentosEntity) {
        return documentoRepositoryPort.salvar(documentosEntity);
    }

    public DocumentosEntity atualizarDocumento(Long codigoDocumento, DocumentosEntity documentosEntity) {
        return documentoRepositoryPort.atualizarDocumento(codigoDocumento, documentosEntity);
    }

}
