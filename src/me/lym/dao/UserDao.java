package me.lym.dao;

import me.lym.entity.User;

public interface UserDao {
    int newUser(User user);

    int updateInfo(User user);

    User queryById(int userId);

    User queryByUsername(String username);

}
