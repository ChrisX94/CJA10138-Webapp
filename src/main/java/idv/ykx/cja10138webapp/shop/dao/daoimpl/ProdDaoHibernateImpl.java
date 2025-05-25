package idv.ykx.cja10138webapp.shop.dao.daoimpl;

import idv.ykx.cja10138webapp.shop.dao.ProdDao;
import idv.ykx.cja10138webapp.shop.model.ProdType;
import idv.ykx.cja10138webapp.shop.model.Product;
import idv.ykx.cja10138webapp.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProdDaoHibernateImpl implements ProdDao {
    public ProdType getOneProdType(Integer prodTypeId){
        return null;
    }

    public void delete(Integer productId){

    }

    public List<ProdType> findAllType(){
        return null;
    }
    public void update(Product product){

    }
    public void addProduct(Product product){
        // Pending
    }

    @Override
    public Product findByPrimaryKey(Integer productId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try{
            session.beginTransaction();
            Product product = session.get(Product.class, productId);
            session.getTransaction().commit();
            return product;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public List<Product> findAll(){
        return null;
    }

}
