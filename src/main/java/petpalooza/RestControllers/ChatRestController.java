//package petpalooza.RestControllers;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import petpalooza.Entities.Chat;
//
//public class ChatRestController {
//
//    @MessageMapping("/chat.register")
//    @SendTo("/topic/public")
//    public Chat register(@Payload Chat chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }
//
//    @MessageMapping("/chat.send")
//    @SendTo("/topic/public")
//    public Chat sendMessage(@Payload Chat chatMessage) {
//        return chatMessage;
//    }
//}
