package au.usyd.artrader.persistence;

import au.usyd.artrader.domain.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);
    List<User> selectUsersByEmail(String email);
    User selectUserByUserId(long userId);
    void updateUser(User user);
    List<User> selectUsersByUserIds(List<Long> userIds);
}
