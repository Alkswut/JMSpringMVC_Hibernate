package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDao {
    List<User> allUsers();

    void add(User user);

    void delete(User user);

    void editUser(User user);

    User getById(int id);
}
