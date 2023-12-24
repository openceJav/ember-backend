package org.ember.emberbackend.services;

import lombok.RequiredArgsConstructor;
import org.ember.emberbackend.repository.MessageRepository;
import org.ember.emberbackend.models.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository repository;
    private final RoomService roomService;

    public Message saveMessage(Message message) {
        var chatId = roomService.getRoomId(
                message.getSenderId(),
                message.getRecipientId(),
                true
        ).orElseThrow(); // TODO: Create Custom Exception
        message.setChatId(chatId);
        repository.save(message);

        return message;
    }

    public List<Message> findMessages(String senderId, String recipientId) {
        var chatId = roomService.getRoomId(
                senderId,
                recipientId,
                false
        );

        return chatId.map(repository::findByChatId)
                .orElse(new ArrayList<>());

    }
}
