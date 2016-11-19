package com.cotemig.repositories;

import com.cotemig.models.User;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public interface UserRepository {
    public void addUser(User user);
    public void updateUser(User user);
    public List<User> listUser();
    public User getUserById(int id);
    public void removeUser(int id);
}