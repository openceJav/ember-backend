package org.ember.emberbackend.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "chat_rooms")
public class Room {
    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
