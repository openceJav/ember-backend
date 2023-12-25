package org.ember.emberbackend.services;

import lombok.RequiredArgsConstructor;
import org.ember.emberbackend.repository.RoomRepository;
import org.ember.emberbackend.models.Room;
import org.ember.emberbackend.utils.generators.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Optional<String> getRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {
        return roomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(Room::getChatId)
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);

                    }
                    return Optional.empty();
                });
    }

    public String createChatId(String senderId, String recipientId) {
        var chatId = IdGenerator.generateChatId(senderId, recipientId);

        Room senderRecipient = Room.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        Room recipientSender = Room.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        roomRepository.save(senderRecipient);
        roomRepository.save(recipientSender);

        return chatId;
    }
}
