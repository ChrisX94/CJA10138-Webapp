package idv.ykx.cja10138webapp.shop.dao.daoimpl;

import idv.ykx.cja10138webapp.shop.dao.ProdDao;
import idv.ykx.cja10138webapp.shop.model.ProdType;
import idv.ykx.cja10138webapp.shop.model.Product;
import idv.ykx.cja10138webapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProdDaoHibernateImpl implements ProdDao {
    private SessionFactory factory;
    public ProdDaoHibernateImpl() {
        factory = HibernateUtil.getSessionFactory();
    }
    private Session getSession() {
        return factory.getCurrentSession();
    }
    public ProdType getOneProdType(Integer prodTypeId) {
        return getSession().get(ProdType.class, prodTypeId);
    }

    public void delete(Integer productId) {

    }

    public List<ProdType> findAllType() {
        return getSession().createQuery("from ProdType", ProdType.class).list();
    }

    public void update(Product product) {
        Session session = getSession();
        session.update(product);
        session.flush(); // 把資料寫入資料庫
        session.clear(); // 清除 session 的 cache
    }

    public Product addProduct(Product product) {
        Session session = getSession();
        Integer id = null;
        id = (Integer) session.save(product);
        session.flush(); // 把資料寫入資料庫
        session.clear(); // 清除 session 的 cache
        return findByPrimaryKey(id);
    }

    @Override
    public Product findByPrimaryKey(Integer productId) {
            return getSession().get(Product.class, productId);

    }

    public List<Product> findAll() {
        return getSession().createQuery("from Product", Product.class).list();
    }

}
