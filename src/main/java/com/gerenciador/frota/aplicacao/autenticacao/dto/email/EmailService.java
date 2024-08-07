package com.gerenciador.frota.aplicacao.autenticacao.dto.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender emailService;

    @Value("${ambiente.execucao}")
    private String ambiente;

    public void enviarEmail (String assunto, String corpoEmail, String emailDestinatario) {
        MimeMessage mimeMessage = emailService.createMimeMessage();
        try {
            log.info("[START] - Processo de envio de email para o novo usuario.");
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("naoresponda@gmail.com");
            helper.setSubject(assunto);
            if (ambiente.equalsIgnoreCase("DEV")){
                helper.setTo("daniellopes0208@hotmail.com");
                log.info("[INFO] -  Email em abiente dev foi enviado para " + emailDestinatario);
            } else {
                helper.setTo(emailDestinatario);
            }
            helper.setText(corpoEmail, true);
            emailService.send(mimeMessage);
            log.info("[END] - Email enviado com sucesso.");
        } catch (MessagingException e) {
            log.info("[ERRO] - Email não foi enviado.");
            throw new RuntimeException(e);
        }
    }

    public String carregarTemplate() throws IOException {
        ClassPathResource resource = new ClassPathResource("/templates/emailCredenciais.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

}
