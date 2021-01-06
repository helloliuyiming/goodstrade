package goodstrade.service;

import goodstrade.entity.User;

public interface UserService {
    boolean register(User user);

    String isExistUser(String username);

    String checkLogin(String username, String password);

    String update(User user);    //	修改个人信息

    User getUser(String username);//	通过用户名获取用户，可以没有

    User getUser(int uid);//	通过用户id获取用户，可以没有
}
