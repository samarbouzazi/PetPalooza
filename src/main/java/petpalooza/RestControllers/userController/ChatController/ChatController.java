package petpalooza.RestControllers.userController.ChatController;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import petpalooza.Entities.ChatMessage;
import petpalooza.Entities.User;

@Controller
@RequestMapping("/public/chat")
public class ChatController {

    @MessageMapping("/chat.register")
    @SendTo("/public/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        User user=new User();
        headerAccessor.getSessionAttributes().put(/*"username"*/ user.getUsername(), chatMessage.getUserID());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

}


