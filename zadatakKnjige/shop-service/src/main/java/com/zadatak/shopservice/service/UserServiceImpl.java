package com.zadatak.shopservice.service;

import com.zadatak.shopservice.dto.UserDto;
import com.zadatak.shopservice.model.User;
import com.zadatak.shopservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(UserDto user) {
        return userRepository.save(new User(user.getUsername(), user.getPassword(), user.getRole()));
    }

    @Override
    public boolean login(String username, String password) {
        List<User> all = userRepository.findAll();
        for (User u : all) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
