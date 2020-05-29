package au.usyd.artrader.persistence;

import au.usyd.artrader.domain.Artwork;
import au.usyd.artrader.util.CommonUtil;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public class ArtworkDaoImpl implements ArtworkDao {

    private final static Logger logger = LoggerFactory.getLogger(ArtworkDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createArtwork(Artwork artwork) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        artwork.setCreatedTimestamp(currentTimestamp);
        artwork.setModifiedTimestamp(currentTimestamp);

        sessionFactory.getCurrentSession().save(artwork);
    }

    @Override
    public List<Artwork> selectArtworksByArtistId(long artistId, String category, int offset, int size) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Artwork.class)
                .add(Restrictions.eq("artistId", artistId));

        if(CommonUtil.isNotEmpty(category)) {
            criteria.add(Restrictions.eq("category", category));
        }

        return criteria.addOrder(Order.desc("modifiedTimestamp"))
                .setFirstResult(offset)
                .setMaxResults(size)
                .list();
    }

    @Override
    public List<Artwork> selectArtworksByOwnerId(long ownerId, String category, int offset, int size) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Artwork.class)
                .add(Restrictions.eq("ownerId", ownerId));

        if(CommonUtil.isNotEmpty(category)) {
            criteria.add(Restrictions.eq("category", category));
        }

        return criteria.addOrder(Order.desc("modifiedTimestamp"))
                .setFirstResult(offset)
                .setMaxResults(size)
                .list();
    }

    @Override
    public long countArtworksByArtistId(long artistId, String category) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Artwork.class)
                .add(Restrictions.eq("artistId", artistId));

        if(CommonUtil.isNotEmpty(category)) {
            criteria.add(Restrictions.eq("category", category));
        }

        return (long) criteria.setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public long countArtworksByOwnerId(long ownerId, String category) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Artwork.class)
                .add(Restrictions.eq("ownerId", ownerId));

        if(CommonUtil.isNotEmpty(category)) {
            criteria.add(Restrictions.eq("category", category));
        }

        return (long) criteria.setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public Artwork selectArtwork(long artworkId) {
        return (Artwork) sessionFactory.getCurrentSession().get(Artwork.class, artworkId);
    }

    @Override
    public void updateArtwork(Artwork artwork) {
        sessionFactory.getCurrentSession().update(artwork);
    }
}
