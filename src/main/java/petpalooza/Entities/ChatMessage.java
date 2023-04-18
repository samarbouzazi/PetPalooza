package petpalooza.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@Table(name = "chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idMessage;
         String content;
         Long  userID;
         MessageType type;

        public enum MessageType {
            CHAT, LEAVE, JOIN
        }

    }


