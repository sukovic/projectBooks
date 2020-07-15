package com.zadatak.shopservice.service;

import com.zadatak.shopservice.dto.UserDto;
import com.zadatak.shopservice.model.User;

public interface UserService {
    User register(UserDto user);

    boolean login(String username, String password);
}
