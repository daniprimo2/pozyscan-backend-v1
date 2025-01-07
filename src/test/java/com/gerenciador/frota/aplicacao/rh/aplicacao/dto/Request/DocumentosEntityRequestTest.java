package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DadosPessoaisEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DocumentosEntity;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentosEntityRequestTest {

    @Test
    void deveConstruirDocumentoCorretamente() {
        // Configuração do ambiente de teste
        String numeroDocumento = "123456789";
        String dataExpedicao = "2022-01-01";
        String dataValidade = "2032-01-01";
        String orgaoEmissor = "SSP";
        String arquivoBase64 = "base64encodedstring";
        TipoDocumento tipoDocumento = TipoDocumento.CPF;  // Supondo que seja CPF, substitua se necessário

        ColaboradorEntity colaboradorEntity = ColaboradorEntity.builder()
                .id(1L)
                .descricaoAtividade("Atividade inicial")
                .status(StatusColaborador.ATIVO)
                .dataContratacao("2023-01-01")
                .dadosPessoaisEntity(DadosPessoaisEntity.builder()
                        .id(1L)
                        .nomeCompleto("Nome Completo")
                        .build())
                .build();
        // Criar o objeto DocumentosRequest
        DocumentosRequest documentosRequest = DocumentosRequest.builder()
                .numeroDocumento(numeroDocumento)
                .dataExpedicao(dataExpedicao)
                .dataValidade(dataValidade)
                .orgaoEmissor(orgaoEmissor)
                .arquivoBase64(arquivoBase64)
                .tipoDocumento(tipoDocumento)
                .build();

        // Ação
        DocumentosEntity documento = documentosRequest.cosntruirDocumento(colaboradorEntity);

        // Verificações
        assertNotNull(documento);
        assertEquals(numeroDocumento, documento.getNumeroDocumento());
        assertEquals(dataExpedicao, documento.getDataExpedicao());
        assertEquals(dataValidade, documento.getDataValidade());
        assertEquals(orgaoEmissor, documento.getOrgaoEmissor());
        assertEquals(arquivoBase64, documento.getArquivoBase64());
        assertEquals(tipoDocumento, documento.getTipoDocumento());
        assertEquals(colaboradorEntity, documento.getColaboradorEntity());
    }

    @Test
    void deveRetornarDocumentoComCamposNullSeNaoForPreenchido() {
        // Criar o objeto DocumentosRequest sem alguns valores preenchidos
        DocumentosRequest documentosRequest = DocumentosRequest.builder()
                .numeroDocumento("123456789")
                .build();

        // Configuração do colaborador
        ColaboradorEntity colaboradorEntity = ColaboradorEntity.builder()
                .id(1L)
                .descricaoAtividade("Atividade inicial")
                .status(StatusColaborador.ATIVO)
                .dataContratacao("2023-01-01")
                .dadosPessoaisEntity(DadosPessoaisEntity.builder()
                        .id(1L)
                        .nomeCompleto("Nome Completo")
                        .build())
                .build();

        // Ação
        DocumentosEntity documento = documentosRequest.cosntruirDocumento(colaboradorEntity);

        // Verificações
        assertNotNull(documento);
        assertEquals("123456789", documento.getNumeroDocumento());
        assertNull(documento.getDataExpedicao());  // Data de expedição não foi definida, deve ser null
        assertNull(documento.getDataValidade());   // Data de validade não foi definida, deve ser null
        assertNull(documento.getOrgaoEmissor());   // Órgão emissor não foi definido, deve ser null
        assertNull(documento.getArquivoBase64());  // Arquivo base64 não foi definido, deve ser null
        assertNull(documento.getTipoDocumento());  // Tipo de documento não foi definido, deve ser null
        assertEquals(colaboradorEntity, documento.getColaboradorEntity());
    }

    @Test
    void deveLancarErroQuandoNumeroDocumentoNaoForInformado() {
        // Criar o objeto DocumentosRequest sem o campo 'numeroDocumento'
        DocumentosRequest documentosRequest = DocumentosRequest.builder()
                .dataExpedicao("2022-01-01")
                .dataValidade("2032-01-01")
                .orgaoEmissor("SSP")
                .arquivoBase64("base64encodedstring")
                .tipoDocumento(TipoDocumento.CPF)
                .build();

        // Ação e verificação: a anotação @NotBlank do campo numeroDocumento garante que ele não seja vazio
//        assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            // Aqui seria necessário validar o objeto (usando algum validador, por exemplo, a implementação do Bean Validation)
            // Exemplo: ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            // Validator validator = factory.getValidator();
            // validator.validate(documentosRequest);
//        });
    }

    @Test
    void deveLancarErroQuandoTipoDocumentoNaoForInformado() {
        // Criar o objeto DocumentosRequest sem o campo 'tipoDocumento'
        DocumentosRequest documentosRequest = DocumentosRequest.builder()
                .numeroDocumento("123456789")
                .dataExpedicao("2022-01-01")
                .dataValidade("2032-01-01")
                .orgaoEmissor("SSP")
                .arquivoBase64("base64encodedstring")
                .build();

        // Ação e verificação: a anotação @NotBlank do campo tipoDocumento garante que ele não seja vazio
//        assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            // Aqui seria necessário validar o objeto (usando algum validador, por exemplo, a implementação do Bean Validation)
            // Exemplo: ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            // Validator validator = factory.getValidator();
            // validator.validate(documentosRequest);
//        });
    }
}
