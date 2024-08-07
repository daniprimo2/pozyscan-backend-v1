package com.gerenciador.frota.aplicacao.Util.Strings;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Utils {

    public static String tratarPegarPrimeiroNome(String nomeCompleto) {
        String [] divisaoDosNomes = nomeCompleto.split(" ");
        return divisaoDosNomes[0];
    }

    public static String conversaoData (LocalDate data) {
        StringBuilder concat = new StringBuilder();
        concat.append(tratarSting(data.getDayOfMonth()));
        concat.append("-");
        concat.append(tratarSting(data.getMonthValue()));
        concat.append("-");
        concat.append(tratarSting(data.getYear()));
        return concat.toString();
    }

    public static String tratarSting(int valor) {
        return String.format("%02d", valor);
    }
    public static String tratarSting(Double valor) {
        return String.format("%,.2f", valor);
    }
    public static String tratarSting(BigDecimal valor) {
        return String.format("%,.2f", valor);
    }

}
