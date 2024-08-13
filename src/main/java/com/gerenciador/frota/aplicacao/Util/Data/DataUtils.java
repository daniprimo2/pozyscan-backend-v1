package com.gerenciador.frota.aplicacao.Util.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DataUtils {


    public static String converterLocalDateParaNossoPadraoData(LocalDate localDate){
        String data = localDate.toString();
        String[] split = data.split("-");
        StringBuilder builder = new StringBuilder();
        builder.append(split[2]);
        builder.append("/");
        builder.append(split[1]);
        builder.append("/");
        builder.append(split[0]);
        return builder.toString();
    }

    public static long calcularDiferencaEmDias(String data1Str, String data2Str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converter strings para LocalDate
        LocalDate data1 = LocalDate.parse(data1Str, formatter);
        LocalDate data2 = LocalDate.parse(data2Str, formatter);

        // Calcular a diferença em dias
        return ChronoUnit.DAYS.between(data1, data2);
    }

}
