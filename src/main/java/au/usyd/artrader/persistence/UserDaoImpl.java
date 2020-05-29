package au.usyd.artrader.persistence;

import au.usyd.artrader.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createUser(User user) {

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        user.setCreatedTimestamp(currentTimestamp);
        user.setModifiedTimestamp(currentTimestamp);

        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> selectUsersByEmail(String email) {
        String selectQuery = "FROM User WHERE email = :emailParam";
        Query query = sessionFactory.getCurrentSession().createQuery(selectQuery);
        query.setParameter("emailParam", email);
        return query.list();
    }

    @Override
    public User selectUserByUserId(long userId) {
        return (User) sessionFactory.getCurrentSession().get(User.class, userId);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public List<User> selectUsersByUserIds(List<Long> userIds) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM User WHERE userId IN (:userIds)")
                .setParameterList("userIds", userIds)
                .list();
    }
}
