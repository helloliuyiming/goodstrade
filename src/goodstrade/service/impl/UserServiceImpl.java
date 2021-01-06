package goodstrade.service.impl;

import goodstrade.dao.UserDao;
import goodstrade.dao.impl.UserDaoImpl;
import goodstrade.entity.User;
import goodstrade.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    //	注册功能
    public boolean register(User user) {
        int insert = userDao.insert(user);
        return insert == 1;
    }

    //	判断用户名是否存在
    public String isExistUser(String username) {
        User result = userDao.selectUser(username);
        if (result != null) {    //	用户已存在
            return "用户名已存在!";
        } else {
            return "用户名可用!";
        }
    }

    //	验证登录
    public String checkLogin(String username, String password) {
        User result = userDao.selectUser(username);
        if (result != null) {    //	用户存在
            if (result.getPassword().equals(password)) {
                return "登录成功!";
            } else {
                return "密码错误,登录失败!";
            }
        } else {
            return "用户名不存在!";
        }
    }

    //	修改个人信息
    public String update(User user) {
        int result = userDao.update(user);
        if (result == 1) {
            return "修改成功!";
        } else {
            return "修改失败,请稍后重试!";
        }
    }

    //	通过用户名获取用户信息
    @Override
    public User getUser(String username) {
        User user = userDao.selectUser(username);
        return user;
    }

    @Override
    public User getUser(int uid) {
        User user = userDao.selectUser(uid);
        return user;
    }

}
