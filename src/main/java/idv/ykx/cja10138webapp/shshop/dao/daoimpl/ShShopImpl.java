package idv.ykx.cja10138webapp.shshop.dao.daoimpl;

import idv.ykx.cja10138webapp.shshop.dao.ShShopDao;
import idv.ykx.cja10138webapp.shshop.model.ShProd;
import idv.ykx.cja10138webapp.shshop.model.ShProdType;
import idv.ykx.cja10138webapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ShShopImpl implements ShShopDao {
    private SessionFactory factory;

    public ShShopImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    /* 練習階段先不用 */
//    private Session getSession() {
//        return factory.getCurrentSession();
//    }

    public List<ShProd> getProdByUserId(Integer userId){
        Session session = factory.openSession();
        List<ShProd> list = null;
        try{
            session.beginTransaction();
            list = session.createQuery("SELECT DISTINCT p FROM ShProd p LEFT JOIN FETCH p.prodPics WHERE p.user.userId = :userId", ShProd.class)
                    .setParameter("userId", userId).list();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        System.out.println(list);
        return list;

    }


    public List<ShProdType>findAllTypes(){
        Session session = factory.getCurrentSession();
        List<ShProdType> list = null;

        try{
            session.beginTransaction();
            list = session.createQuery("FROM ShProdType", ShProdType.class).list();
            session.getTransaction().commit();
            System.out.println("daoImlp " + list);
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        System.out.println("daoImlp " + list);
        return list;
    }

    public ShProd updateProd(ShProd prod) {
        Session session = factory.getCurrentSession();
        try{
            session.beginTransaction();
            ShProd updatedProd = (ShProd) session.merge(prod);
            session.getTransaction().commit();
            return updatedProd;
        }catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public ShProd addShProd(ShProd shProd){
        Session session = factory.getCurrentSession();
        try{
            session.beginTransaction();
            session.save(shProd);
            session.getTransaction().commit();
            return shProd;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return shProd;
    }


    @Override
    public List<ShProd> findAll(){
        Session session = factory.getCurrentSession();
        List<ShProd> list = null;
        try{
            session.beginTransaction();
            list = session.createQuery("from ShProd").list();
            session.getTransaction().commit();
            return list;
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public ShProd getOneByPK(Integer prodId){
        Session session = factory.getCurrentSession();
        ShProd shProd = null;
        try{
            session.beginTransaction();
//            shProd = session.get(ShProd.class, prodId);
            shProd = session.createQuery(
                            "SELECT DISTINCT p FROM ShProd p " +
                                    "LEFT JOIN FETCH p.prodPics " +
                                    "LEFT JOIN FETCH p.shProdType " +
                                    "LEFT JOIN FETCH p.user " +
                                    "WHERE p.prodId = :prodId", ShProd.class)
                    .setParameter("prodId", prodId)
                    .uniqueResult();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return shProd;
    }

    public List<ShProd> findAllWithPicsAndType(){
        Session session = factory.getCurrentSession();
        List<ShProd> ShProds = null;
        try {
            session.beginTransaction();
            ShProds = session.createQuery(
                    "SELECT DISTINCT p FROM ShProd p " +
                            "LEFT JOIN FETCH p.prodPics " +
                            "LEFT JOIN FETCH p.shProdType " +
                            "LEFT JOIN FETCH p.user",
                    ShProd.class
            ).getResultList();
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return ShProds;
    }
}
