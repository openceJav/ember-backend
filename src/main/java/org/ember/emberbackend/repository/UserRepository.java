package org.ember.emberbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.ember.emberbackend.models.User;
import org.ember.emberbackend.enums.Status;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
