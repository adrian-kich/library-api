package com.adriankich.email.service;

import com.adriankich.email.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendMailPaymentProcessing(PaymentDTO paymentDTO) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(paymentDTO.renterMail());
            simpleMailMessage.setSubject("Devolução de livros - Pagamento em processamento");
            simpleMailMessage.setText(getHtmlProcessing(paymentDTO.renterName(), paymentDTO.id(), paymentDTO.value()));

            javaMailSender.send(simpleMailMessage);
            System.out.println("Email de Pagamento sendo processado enviado: PaymentId = " + paymentDTO.id());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMailPaymentProcessed(PaymentDTO paymentDTO) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(paymentDTO.renterMail());
            simpleMailMessage.setSubject("Devolução de livros - Pagamento Processado");
            simpleMailMessage.setText(getHtmlProcessed(paymentDTO.renterName(), paymentDTO.id(), paymentDTO.value()));

            javaMailSender.send(simpleMailMessage);
            System.out.println("Email de Pagamento processado enviado: PaymentId = " + paymentDTO.id());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getHtmlProcessing(String name, Long id, Double value) {
        return "<html>" +
                "<body>" +
                "<h1 style='color:blue;'>Olá, " + name + "!</h1>" +
                "<p>Obrigado por fazer a devolução dos livros, seu pagamento está sendo processado.</p>" +
                "<p>Pagamento: #" + id + " Valor: R$" + value + ".</p>" +
                "</body>" +
                "</html>";
    }

    public String getHtmlProcessed(String name, Long id, Double value) {
        return "<html>" +
                "<body>" +
                "<h1 style='color:blue;'>Olá, " + name + "!</h1>" +
                "<p>Seu pagamento foi processado com sucesso.</p>" +
                "<p>Pagamento: #" + id + " Valor: R$" + value + ".</p>" +
                "</body>" +
                "</html>";
    }
}
