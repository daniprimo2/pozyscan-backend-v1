package com.gerenciador.frota.aplicacao.Util.Html;

import com.gerenciador.frota.aplicacao.Util.Strings.Utils;
import com.gerenciador.frota.aplicacao.autenticacao.dto.ParametrosEmailRequest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class UtilHtml {

    public static Map<String, String> construirParametrosHtml(ParametrosEmailRequest parametrosEmail) {
        Map<String, String> paransMap = new LinkedHashMap<>();
        String nomeParticipante = "#nomeParticipante#";
        String login = "#login#";
        String senha = "#senha#";

        paransMap.put(nomeParticipante, Utils.tratarPegarPrimeiroNome(parametrosEmail.getNomeParticipante()));
        paransMap.put(login, parametrosEmail.getLogin());
        paransMap.put(senha, parametrosEmail.getSenha());
        return paransMap;
    }

    public static String emailHtmlToString (String emailPath, Map<String, String> params){
        String emailContent = htmlToString(emailPath);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            emailContent = emailContent.replaceAll(entry.getKey(), entry.getValue());
        }
        return emailContent;
    }

    private static String htmlToString(String emailPath) {

        try {
            ClassPathResource resource = new ClassPathResource(emailPath);
            return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
