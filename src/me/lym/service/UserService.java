package me.lym.service;

import me.lym.entity.User;

public interface UserService {
    int newUser(User user);

    int updateInfo(User user);

    User queryById(int userId);

    User queryByUsername(String username);
}
