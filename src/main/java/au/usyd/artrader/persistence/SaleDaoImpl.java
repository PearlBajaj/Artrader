package au.usyd.artrader.persistence;

import au.usyd.artrader.domain.Sale;
import au.usyd.artrader.util.CommonUtil;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
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
public class SaleDaoImpl implements SaleDao {

    private static final Logger logger = LoggerFactory.getLogger(SaleDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createSale(Sale sale) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        sale.setCreatedTimestamp(currentTimestamp);
        sale.setModifiedTimestamp(currentTimestamp);
        sessionFactory.getCurrentSession().save(sale);
    }

    @Override
    public List<Sale> getSalesBySellerId(long sellerId, int offset, int size) {
        return sessionFactory.getCurrentSession().createCriteria(Sale.class)
                .add(Restrictions.eq("sellerId", sellerId))
                .addOrder(Order.desc("modifiedTimestamp"))
                .setFirstResult(offset)
                .setMaxResults(size)
                .list();
    }

    @Override
    public long countSalesBySellerId(long sellerId) {
        return (long) sessionFactory.getCurrentSession().createCriteria(Sale.class)
                .add(Restrictions.eq("sellerId", sellerId))
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public Sale getSale(long saleId) {
        return (Sale) sessionFactory.getCurrentSession().get(Sale.class, saleId);
    }

    @Override
    public void updateSale(Sale sale) {
        sessionFactory.getCurrentSession().update(sale);
    }

    @Override
    public void deleteSale(Sale sale) {
        sessionFactory.getCurrentSession().delete(sale);
    }

    @Override
    public List<Sale> selectSales(String category, List<String> keywords, int offset, int size) {
        Criteria criteria = createCriteria(category, keywords);

        return criteria.addOrder(Order.desc("modifiedTimestamp"))
                .setFirstResult(offset)
                .setMaxResults(size)
                .list();
    }

    @Override
    public long countSales(String category, List<String> keywords) {
        Criteria criteria = createCriteria(category, keywords);

        return (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria createCriteria(String category, List<String> keywords) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Sale.class, "sale");

        if(CommonUtil.isNotEmpty(category) || !keywords.isEmpty()) {
            criteria.createCriteria("sale.artwork", "artwork")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        }

        if(CommonUtil.isNotEmpty(category)) {
            criteria.add(Restrictions.eq("artwork.category", category));
        }

        if(!keywords.isEmpty()) {
            keywords.forEach(keyword -> criteria.add(Restrictions.like("artwork.name", keyword, MatchMode.ANYWHERE)));
        }

        return criteria;
    }

    @Override
    public List<Sale> selectSales(List<Long> artworkIds) {
        return sessionFactory.getCurrentSession().createQuery("FROM Sale WHERE artworkId IN (:artworkIds)")
                .setParameterList("artworkIds", artworkIds)
                .list();
    }
}
