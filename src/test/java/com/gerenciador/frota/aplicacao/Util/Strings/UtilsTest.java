package com.gerenciador.frota.aplicacao.Util.Strings;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    // Testando o método tratarPegarPrimeiroNome
    @Test
    void testTratarPegarPrimeiroNome_comNomeCompleto() {
        String nomeCompleto = "João da Silva";
        String primeiroNome = Utils.tratarPegarPrimeiroNome(nomeCompleto);
        assertEquals("João", primeiroNome);
    }

    @Test
    void testTratarPegarPrimeiroNome_comNomeSimples() {
        String nomeCompleto = "Maria";
        String primeiroNome = Utils.tratarPegarPrimeiroNome(nomeCompleto);
        assertEquals("Maria", primeiroNome);
    }

    @Test
    void testTratarPegarPrimeiroNome_comNomeVazio() {
        String nomeCompleto = "";
        String primeiroNome = Utils.tratarPegarPrimeiroNome(nomeCompleto);
        assertEquals("", primeiroNome);  // Retorna vazio se não houver nome
    }

    // Testando o método conversaoData
    @Test
    void testConversaoData_comDataValida() {
        LocalDate data = LocalDate.of(2024, 12, 10);
        String dataFormatada = Utils.conversaoData(data);
        assertEquals("10-12-2024", dataFormatada);
    }

    @Test
    void testConversaoData_comDataComDiaUnico() {
        LocalDate data = LocalDate.of(2024, 1, 1);
        String dataFormatada = Utils.conversaoData(data);
        assertEquals("01-01-2024", dataFormatada);  // Verifica se o dia é formatado com 2 dígitos
    }

    @Test
    void testConversaoData_comDataComMesUnico() {
        LocalDate data = LocalDate.of(2024, 11, 25);
        String dataFormatada = Utils.conversaoData(data);
        assertEquals("25-11-2024", dataFormatada);  // Verifica se o mês é formatado com 2 dígitos
    }

    // Testando o método tratarSting para tipos int
    @Test
    void testTratarSting_comInteiro() {
        int valor = 5;
        String valorFormatado = Utils.tratarSting(valor);
        assertEquals("05", valorFormatado);  // Verifica se o valor é formatado com 2 dígitos
    }

    // Testando o método tratarSting para tipos Double
    @Test
    void testTratarSting_comDouble() {
        Double valor = 1234.5;
        String valorFormatado = Utils.tratarSting(valor);
        assertEquals("1.234,50", valorFormatado);  // Verifica a formatação correta de número com vírgula e 2 casas decimais
    }

    @Test
    void testTratarSting_comDoubleSemCasasDecimais() {
        Double valor = 1000.0;
        String valorFormatado = Utils.tratarSting(valor);
        assertEquals("1.000,00", valorFormatado);  // Verifica se o número sem casas decimais ainda é formatado corretamente
    }

    // Testando o método tratarSting para tipos BigDecimal
    @Test
    void testTratarSting_comBigDecimal() {
        BigDecimal valor = new BigDecimal("1234567.89");
        String valorFormatado = Utils.tratarSting(valor);
        assertEquals("1.234.567,89", valorFormatado);  // Verifica a formatação com vírgulas e 2 casas decimais
    }

    @Test
    void testTratarSting_comBigDecimalSemCasasDecimais() {
        BigDecimal valor = new BigDecimal("1000");
        String valorFormatado = Utils.tratarSting(valor);
        assertEquals("1.000,00", valorFormatado);  // Verifica se o BigDecimal sem casas decimais é formatado corretamente
    }
}
