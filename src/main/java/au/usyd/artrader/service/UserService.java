package au.usyd.artrader.service;

import au.usyd.artrader.domain.User;

import java.util.List;

public interface UserService {
    void signUp(User user);
    User getUser(String email);
    User getUser(long userId);
    void editUser(User user);
    List<User> getUsers(List<Long> userIds);
}
