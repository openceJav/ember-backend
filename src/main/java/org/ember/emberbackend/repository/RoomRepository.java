package org.ember.emberbackend.repository;

import org.ember.emberbackend.models.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
