package me.lym.service.impl;

import me.lym.dao.UserDao;
import me.lym.entity.User;
import me.lym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int newUser(User user) {
        return userDao.newUser(user);
    }

    @Override
    public int updateInfo(User user) {
        return userDao.updateInfo(user);
    }

    @Override
    public User queryById(int userId) {
        return userDao.queryById(userId);
    }

    @Override
    public User queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }

}
