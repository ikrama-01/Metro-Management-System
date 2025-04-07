package com.metro.service;

import com.metro.model.User;

public interface UserService {
    User registerUser(User user);
    User getUserByEmail(String email);
}
