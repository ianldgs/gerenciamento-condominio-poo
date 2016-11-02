package com.cotemig.dao;

import com.cotemig.model.User;

import java.util.List;

/**
 * Created by matheus.elias on 11/2/16.
 */
public interface UserDAO {
    public void addUser();
    public void updateUser(User user);
    public List<User> listUser();
    public User getUserById(int id);
    public void removeUser(int id);
}
