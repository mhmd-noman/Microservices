package com.pmis.user.service;

import com.pmis.user.service.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService  {
    User saveUser(User user);
    List<User> getUsers();
    User getUser(String id);
    String deleteUser(String id);
}
