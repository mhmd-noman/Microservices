package com.pmis.user.service;

import com.pmis.user.service.exceptions.UserNotFoundException;
import com.pmis.user.service.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    List<User> users = new ArrayList<>();
    @Override
    public User save(User user) {
        if (null == user.getId() || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        users.add(user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUserById(String id) {
        return users.stream()
                .filter(user -> user.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found with Id: "+ id));
    }

    @Override
    public String deleteUserById(String id) {
        User user = users.stream().filter(u->u.getId().equalsIgnoreCase(id)).findFirst().get();
        users.remove(user);
        return "User is deleted with Id: ["+ id+ "]";
    }
}
