package com.adriankich.email.service;

import com.adriankich.email.dto.PaymentDTO;
import com.adriankich.email.enums.EmailStatus;
import com.adriankich.email.enums.EmailType;
import com.adriankich.email.mapper.EmailMapper;
import com.adriankich.email.model.Email;
import com.adriankich.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendMailPaymentProcessing(PaymentDTO paymentDTO) {
        Email email = EmailMapper.dtoToEntity(paymentDTO);

        email.setSender(sender);
        email.setType(EmailType.IN_PROCESSING_MESSAGE);
        email.setSubject("Devolução de livros - Pagamento em processamento");
        email.setText(getHtmlProcessing(paymentDTO.renterName(), paymentDTO.id(), paymentDTO.value()));

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(email.getSender());
            simpleMailMessage.setTo(email.getReceiver());
            simpleMailMessage.setSubject(email.getSubject());
            simpleMailMessage.setText(email.getText());

            javaMailSender.send(simpleMailMessage);
            System.out.println("Email de Pagamento sendo processado enviado: PaymentId = " + paymentDTO.id());

            email.setStatus(EmailStatus.SEND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            email.setStatus(EmailStatus.ERROR_SEND);
        }

        emailRepository.save(email);
    }

    public void sendMailPaymentProcessed(PaymentDTO paymentDTO) {
        Email email = EmailMapper.dtoToEntity(paymentDTO);

        email.setSender(sender);
        email.setType(EmailType.PROCESSED_MESSAGE);
        email.setSubject("Devolução de livros - Pagamento Processado");
        email.setText(getHtmlProcessed(paymentDTO.renterName(), paymentDTO.id(), paymentDTO.value()));

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(email.getSender());
            simpleMailMessage.setTo(email.getReceiver());
            simpleMailMessage.setSubject(email.getSubject());
            simpleMailMessage.setText(email.getText());

            javaMailSender.send(simpleMailMessage);
            System.out.println("Email de Pagamento processado enviado: PaymentId = " + paymentDTO.id());

            email.setStatus(EmailStatus.SEND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            email.setStatus(EmailStatus.ERROR_SEND);
        }

        emailRepository.save(email);
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
