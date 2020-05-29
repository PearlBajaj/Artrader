package au.usyd.artrader.service;

import au.usyd.artrader.domain.User;
import au.usyd.artrader.persistence.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public void signUp(User user) {
        //TODO: Check if email is existing
        userDao.createUser(user);
    }

    @Override
    public User getUser(String email) {
        List<User> users = userDao.selectUsersByEmail(email);

        if (ObjectUtils.isEmpty(users)) {
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public User getUser(long userId) {
        return userDao.selectUserByUserId(userId);
    }

    @Override
    public void editUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List<User> getUsers(List<Long> userIds) {
        if(userIds.isEmpty()) {
            return Collections.emptyList();
        }
        return userDao.selectUsersByUserIds(userIds);
    }
}
