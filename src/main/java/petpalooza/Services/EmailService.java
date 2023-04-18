package petpalooza.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
    @AllArgsConstructor
    @Slf4j
    public class EmailService implements EmailSender {



        private final JavaMailSender mailSender;

        @Override
        @Async
        public void send(String to, String email) {
            try {

                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper =
                        new MimeMessageHelper(mimeMessage, "utf-8");
                helper.setText(email, true);
                helper.setTo(to);
                helper.setSubject("on a une opportunieté pour vous");
                helper.setFrom("Petpalooza@contact.tn");
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                log.error("failed to send email "+e);

                throw new IllegalStateException("failed to send email");
            }
        }
}
