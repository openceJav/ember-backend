package org.ember.emberbackend.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {
    @Id
    private String id;
    private String senderId;
    private String recipientId;
    private String messageContent;
}
