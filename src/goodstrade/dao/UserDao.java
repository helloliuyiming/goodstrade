package goodstrade.dao;

import goodstrade.entity.User;

import java.util.ArrayList;

public interface UserDao {
    int insert(User user);

    int update(User user);

    User selectUser(String username);

    User selectUser(int uid);

    ArrayList<User> selectAllUser();
}
