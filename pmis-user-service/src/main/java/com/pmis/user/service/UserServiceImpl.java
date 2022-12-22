package com.pmis.user.service;

import com.pmis.user.service.exceptions.UserNotFoundException;
import com.pmis.user.service.models.User;
import com.pmis.user.service.repository.UserRepository;
import com.pmis.user.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        if (null == user.getId() || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        if (Utils.ifEmptyList(users)){
            throw new UserNotFoundException("No User found!");
        }
        return users;
    }

    @Override
    public User getUser(String id) {
        User user = userRepository.findById(id).get();
        if (null == user){
            throw new UserNotFoundException("User not found with Id: "+ id);
        }
        return user;
    }

    @Override
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User is deleted with Id: [\"+ id+ \"]";
    }
}