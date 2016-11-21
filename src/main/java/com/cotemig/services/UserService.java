package com.cotemig.services;

import com.cotemig.exceptions.InvalidEmailOrPasswordException;
import com.cotemig.models.User;
import com.cotemig.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ian Luca on 20/11/2016.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) throws InvalidEmailOrPasswordException {
        //TODO: hash password
        User user = userRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            return user;
        } else {
            throw new InvalidEmailOrPasswordException();
        }
    }
}
