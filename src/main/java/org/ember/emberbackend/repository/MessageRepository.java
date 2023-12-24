package org.ember.emberbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.ember.emberbackend.models.Message;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByChatId(String s);
}
