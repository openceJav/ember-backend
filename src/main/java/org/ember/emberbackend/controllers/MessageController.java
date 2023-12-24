package org.ember.emberbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.ember.emberbackend.models.ChatNotification;
import org.ember.emberbackend.services.MessageService;
import org.ember.emberbackend.models.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {
     private final SimpMessagingTemplate messagingTemplate;
     private final MessageService messageService;

     @MessageMapping("/chat")
     public void processMessage(@Payload Message message){
         Message savedMsg = messageService.saveMessage(message);

         // {user}/queue/messages
         messagingTemplate.convertAndSendToUser(
            message.getRecipientId(),
                 "/queue/messages",
                 ChatNotification.builder()
                         .id(savedMsg.getId())
                         .senderId(savedMsg.getSenderId())
                         .recipientId(savedMsg.getRecipientId())
                         .messageContent(savedMsg.getMessageContent())
                         .build()
         );
     }

     @GetMapping("/messages/{senderId}/{recipientId}")
     public ResponseEntity<List<Message>> findChatMessages(
             @PathVariable("senderId") String senderId,
             @PathVariable("recipientId") String recipientId
     ) {
        return ResponseEntity.ok(messageService.findMessages(senderId, recipientId));

     }

}
