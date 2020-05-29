package au.usyd.artrader.persistence;

import au.usyd.artrader.domain.Favourite;
import au.usyd.artrader.service.FavouriteServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class FavouriteDaoImpl implements FavouriteDao {

    private static final Logger logger = LoggerFactory.getLogger(FavouriteServiceImpl.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void createFavourite(Favourite favourite) {
        sessionFactory.getCurrentSession().save(favourite);
    }

    @Override
    public void deleteFavourite(Favourite favourite) {
        sessionFactory.getCurrentSession().delete(favourite);
    }

    @Override
    public List<Favourite> selectFavourites(Long userId, List<Long> artworkIds) {
        if(ObjectUtils.isEmpty(artworkIds) || userId == null) {
            return Collections.emptyList();
        }

        return sessionFactory.getCurrentSession().createCriteria(Favourite.class)
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.in("artworkId", artworkIds))
                .list();
    }

    @Override
    public Favourite selectFavourite(Long userId, Long artworkId) {
        return (Favourite) sessionFactory.getCurrentSession().createCriteria(Favourite.class)
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.eq("artworkId", artworkId))
                .uniqueResult();
    }
    public List selectFavouritesByUserId(Long userId) {
        if(ObjectUtils.isEmpty(userId)) {
            return Collections.emptyList();
        }
        return sessionFactory.getCurrentSession().createQuery("FROM Favourite WHERE userId = :userId" +
                " order by createdTimestamp desc")
                .setParameter("userId",userId)
                .list();
    }
}
