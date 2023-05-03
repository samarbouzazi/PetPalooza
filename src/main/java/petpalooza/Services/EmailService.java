package petpalooza.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import petpalooza.Entities.Email;
import petpalooza.Entities.User;
import petpalooza.Entities.Event;
import petpalooza.Repositories.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;


@Service
@Slf4j
public class EmailService implements IEmailService{

    @Autowired
    UserRepository userRepository;

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }



    @Override
    public String sendSimpleMail(Event event) {
        String subject = "Nouvel événement ajouté : " + event.getTitre();
        String message = "Cher utilisateur,\n\n" + "Un nouvel événement a été ajouté : " + event.getTitre() +
                "\nLocalisation : " + event.getLocation() + "\nDate de début : " + event.getDateDebut()+
                "\n\nCordialement,\nPetPalooza";
        List<User> users = userRepository.findAll();
        for (User user : users) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
            mailMessage.setText(message);
            mailMessage.setSubject(subject);
            javaMailSender.send(mailMessage);
        }
        return "Mail Sent Successfully...";
    }

    @Override
    public String sendEmail(String email, String subject, String message) {

        List<User> users = userRepository.findAll();
        for (User user : users) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getEmail());
            mailMessage.setText(subject);
            mailMessage.setSubject(message);
            javaMailSender.send(mailMessage);
        }

        return null;
    }

    @Override
    public String sendMailWithAttachment(Email details) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));
            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }


    /////////////// malek

    @Override
    @Async
    public void send(String to, String email) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("on a une opportunieté pour vous");
            helper.setFrom("Petpalooza@contact.tn");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("failed to send email " + e);

            throw new IllegalStateException("failed to send email");
        }
    }


}
