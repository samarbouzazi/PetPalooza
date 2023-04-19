package petpalooza.Services;


import petpalooza.Entities.Email;

public interface IEmailService {

    String sendSimpleMail(Email details);


    String sendMailWithAttachment(Email details);

    void send(String to, String email);
}
