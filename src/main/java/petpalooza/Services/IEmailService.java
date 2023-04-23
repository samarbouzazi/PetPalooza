package petpalooza.Services;


import petpalooza.Entities.Email;
import petpalooza.Entities.Event;

public interface IEmailService {

    String sendSimpleMail(Event event);

    String sendEmail(String email, String subject, String message);


    String sendMailWithAttachment(Email details);

    void send(String to, String email);
}
