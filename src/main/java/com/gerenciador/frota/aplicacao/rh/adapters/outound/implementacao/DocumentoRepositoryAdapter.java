package com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DocumentosEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.DocumentoRepositoryPort;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.DocumentosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class DocumentoRepositoryAdapter implements DocumentoRepositoryPort {

    private final DocumentosRepository documentosRepository;

    @Override
    public DocumentosEntity buscarDocumentoPorTipo(Long codigoDocumento,
                                                   TipoDocumento tipoDocumento) {
        return documentosRepository.findByTipoDocumento(codigoDocumento, tipoDocumento);
    }

    @Override
    public DocumentosEntity salvar(DocumentosEntity documentosEntity) {
        return documentosRepository.save(documentosEntity);
    }

    @Override
    public DocumentosEntity atualizarDocumento(Long codigoDocumento, DocumentosEntity documentosEntity) {
        var documentosRetornado = this.buscarDocumentoPorTipo(codigoDocumento,
                documentosEntity.getTipoDocumento());
        return documentosRepository.save(documentosRetornado.atualizarDocumento(documentosEntity));
    }
}
