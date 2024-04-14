package com.Rasmi.PersonalChatApp.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public Optional<User> disconnect(User user) {
        return repository.findById(user.getNickName()).map(storedUser -> {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
            return storedUser;
        });
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }
}
