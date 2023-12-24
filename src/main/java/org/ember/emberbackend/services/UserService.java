package org.ember.emberbackend.services;

import lombok.RequiredArgsConstructor;
import org.ember.emberbackend.enums.Status;
import org.ember.emberbackend.models.User;
import org.ember.emberbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnectUser(User user) {
        var storedUser = repository.findById(user.getSetUserName())
                .orElse(null);

        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }
}
