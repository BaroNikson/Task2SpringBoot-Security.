package com.example.rus.boot1.service;



import com.example.rus.boot1.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    void addUser(User user);

    void updateUser(int id, User updatedUser);

    void deleteUser(int id);
}
