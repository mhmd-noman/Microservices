package com.pmis.user.service;

import com.pmis.user.service.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService  {
    User save(User user);
    List<User> getUsers();
    User getUserById(String id);
    String deleteUserById(String id);
}
