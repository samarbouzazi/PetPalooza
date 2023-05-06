package petpalooza.Schedular;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import petpalooza.Entities.Event;
import petpalooza.Entities.User;
import petpalooza.Repositories.EventRepository;
import petpalooza.Services.IEmailService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class Schedular {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    IEmailService emailService;

    @Scheduled(cron = "0 33 10 * * *")
    public void sendEmails() {
        Date today = new Date();
        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(today);
        tomorrow.add(Calendar.DATE, 1);
        Date tomorrowDate = tomorrow.getTime();

        List<Event> events = eventRepository.findByDateDebut(tomorrowDate);
        for (Event event : events) {
            // récupérer la liste des utilisateurs intéressés par l'événement
            Set<User> interestedUsers = event.getParticipants();

            // construire le message d'e-mail
            String subject = "Rappel: " + event.getTitre() + " aura lieu demain";
            String message = "Bonjour,\n\nCe message est pour vous rappeler que l'événement " + event.getTitre() + " aura lieu demain à " + event.getLocation() + ".\n\nCordialement,\nL'équipe PetPalooza";

            // envoyer un e-mail à chaque utilisateur intéressé
            for (User user : interestedUsers) {
                emailService.sendEmail(user.getEmail(), subject, message);
            }
        }
    }
}
