package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.EnderecoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotaFiscalLogisticaRequestTest {

    private NotaFiscalLogisticaRequest notaFiscalLogisticaRequest;

    @BeforeEach
    void setUp() {
        notaFiscalLogisticaRequest = NotaFiscalLogisticaRequest.builder()
                .numeroNotaFisal("123456")
                .valorTotal(1000.50)
                .dataEmissao("2023-12-01")
                .enderecoRequest(new EnderecoRequest(
                        "06385820",
                        "Rua Ipixuna",
                        "154",
                        "Vila Menk",
                        "Carapicuíba",
                        "SP",
                        "São Paulo",
                        ""))
                .build();
    }

    @Test
    void getNumeroNotaFisal() {
        assertEquals("123456", notaFiscalLogisticaRequest.getNumeroNotaFisal());
    }

    @Test
    void getValorTotal() {
        assertEquals(1000.50, notaFiscalLogisticaRequest.getValorTotal());
    }

    @Test
    void getDataEmissao() {
        assertEquals("2023-12-01", notaFiscalLogisticaRequest.getDataEmissao());
    }

    @Test
    void getEnderecoRequest() {
        EnderecoRequest endereco = notaFiscalLogisticaRequest.getEnderecoRequest();
        assertNotNull(endereco);
        assertEquals("06385820", endereco.getCep());
        assertEquals("Rua Ipixuna", endereco.getLogradouro());
        assertEquals("154", endereco.getNumero());
        assertEquals("Vila Menk", endereco.getBairro());
        assertEquals("Carapicuíba", endereco.getLocalidade());
        assertEquals("SP", endereco.getEstado());
        assertEquals("São Paulo", endereco.getUf());
    }

    @Test
    void setNumeroNotaFisal() {
        notaFiscalLogisticaRequest.setNumeroNotaFisal("654321");
        assertEquals("654321", notaFiscalLogisticaRequest.getNumeroNotaFisal());
    }

    @Test
    void setValorTotal() {
        notaFiscalLogisticaRequest.setValorTotal(2000.75);
        assertEquals(2000.75, notaFiscalLogisticaRequest.getValorTotal());
    }

    @Test
    void setDataEmissao() {
        notaFiscalLogisticaRequest.setDataEmissao("2024-01-01");
        assertEquals("2024-01-01", notaFiscalLogisticaRequest.getDataEmissao());
    }

    @Test
    void setEnderecoRequest() {
        EnderecoRequest novoEndereco = new EnderecoRequest(
                "12345678",
                "Rua Nova",
                "100",
                "Centro",
                "São Paulo",
                "SP",
                "São Paulo",
                "Apt 101");
        notaFiscalLogisticaRequest.setEnderecoRequest(novoEndereco);

        assertEquals("12345678", notaFiscalLogisticaRequest.getEnderecoRequest().getCep());
        assertEquals("Rua Nova", notaFiscalLogisticaRequest.getEnderecoRequest().getLogradouro());
    }

    @Test
    void testToString() {
        String expected = "NotaFiscalLogisticaRequest(numeroNotaFisal=123456, valorTotal=1000.5, dataEmissao=2023-12-01, " +
                "enderecoRequest=EnderecoRequest(cep=06385820, logradouro=Rua Ipixuna, numero=154, bairro=Vila Menk, " +
                "cidade=Carapicuíba, estado=SP, uf=São Paulo, complemento=))";
        assertEquals(expected, notaFiscalLogisticaRequest.toString());
    }

    @Test
    void builder() {
        EnderecoRequest enderecoRequest = new EnderecoRequest(
                "87654321",
                "Avenida Central",
                "200",
                "Centro",
                "Osasco",
                "SP",
                "São Paulo",
                "");
        NotaFiscalLogisticaRequest novaNota = NotaFiscalLogisticaRequest.builder()
                .numeroNotaFisal("987654")
                .valorTotal(500.00)
                .dataEmissao("2025-01-01")
                .enderecoRequest(enderecoRequest)
                .build();

        assertNotNull(novaNota);
        assertEquals("987654", novaNota.getNumeroNotaFisal());
        assertEquals(500.00, novaNota.getValorTotal());
        assertEquals("2025-01-01", novaNota.getDataEmissao());
        assertEquals("87654321", novaNota.getEnderecoRequest().getCep());
        assertEquals("Avenida Central", novaNota.getEnderecoRequest().getLogradouro());
    }
}
