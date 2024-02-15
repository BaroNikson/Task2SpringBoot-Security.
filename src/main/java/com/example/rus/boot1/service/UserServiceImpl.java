package com.example.rus.boot1.service;


import com.example.rus.boot1.dao.UserDAO;
import com.example.rus.boot1.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    @PersistenceContext
    private EntityManager entityManager;


    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(int id, User updatedUser) {
        // Создаем объект пользователя с заданным идентификатором
        User existingUser = new User();
        existingUser.setId(id);

        // Обновляем все поля из обновленного пользователя
        existingUser.setName(updatedUser.getName());
        existingUser.setSurname(updatedUser.getSurname());
        existingUser.setDepartment(updatedUser.getDepartment());
        existingUser.setSalary(updatedUser.getSalary());

        // Используем merge для обновления или создания пользователя
        entityManager.merge(existingUser);
    }


    @Override
    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }
}
