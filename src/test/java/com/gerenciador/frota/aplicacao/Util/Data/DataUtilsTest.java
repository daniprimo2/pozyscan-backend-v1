package com.gerenciador.frota.aplicacao.Util.Data;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilsTest {

    // Testando o método converterLocalDateParaNossoPadraoData
    @Test
    void testConverterLocalDateParaNossoPadraoData() {
        // Testando a conversão de LocalDate para o formato dd/MM/yyyy
        LocalDate data = LocalDate.of(2024, 12, 10);
        String resultado = DataUtils.converterLocalDateParaNossoPadraoData(data);

        assertEquals("10/12/2024", resultado);
    }

    @Test
    void testConverterLocalDateParaNossoPadraoData_dataComAnoMenorQue10() {
        // Testando a conversão para data de ano com 2 dígitos
        LocalDate data = LocalDate.of(2024, 2, 5);
        String resultado = DataUtils.converterLocalDateParaNossoPadraoData(data);

        assertEquals("05/02/2024", resultado);
    }

    // Testando o método calcularDiferencaEmDias
    @Test
    void testCalcularDiferencaEmDias() {
        // Testando a diferença entre duas datas
        String data1 = "01/01/2024";
        String data2 = "10/01/2024";
        long resultado = DataUtils.calcularDiferencaEmDias(data1, data2);

        assertEquals(9, resultado);
    }

    @Test
    void testCalcularDiferencaEmDias_dataInversa() {
        // Testando a diferença de dias com as datas invertidas
        String data1 = "10/01/2024";
        String data2 = "01/01/2024";
        long resultado = DataUtils.calcularDiferencaEmDias(data1, data2);

        assertEquals(-9, resultado); // Deveria retornar a diferença negativa
    }

    @Test
    void testCalcularDiferencaEmDias_datasIguais() {
        // Testando a diferença entre datas iguais
        String data1 = "01/01/2024";
        String data2 = "01/01/2024";
        long resultado = DataUtils.calcularDiferencaEmDias(data1, data2);

        assertEquals(0, resultado); // A diferença deve ser 0
    }

    @Test
    void testCalcularDiferencaEmDias_datasInvalidas() {
        // Testando caso de datas inválidas
        String data1 = "01/01/2024";
        String data2 = "invalid_date";

        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            DataUtils.calcularDiferencaEmDias(data1, data2);
        });
    }
}
