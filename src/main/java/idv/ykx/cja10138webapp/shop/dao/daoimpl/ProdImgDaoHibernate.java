package idv.ykx.cja10138webapp.shop.dao.daoimpl;

import idv.ykx.cja10138webapp.shop.dao.ProdImgDao;
import idv.ykx.cja10138webapp.shop.model.ProdPic;
import idv.ykx.cja10138webapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProdImgDaoHibernate implements ProdImgDao {
    private SessionFactory factory;
    public ProdImgDaoHibernate() {
        factory = HibernateUtil.getSessionFactory();
    }
    public Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public void uploadPic(ProdPic prodPic) {
        getSession().save(prodPic);
    }

    @Override
    public ProdPic getProdPicById(Integer PROD_PIC_ID) {
        return null;
    }

    @Override
    public List<ProdPic> getProdPicByProdId(Integer prodId) {
        String hql = "from ProdPic p where p.prodId=:prodId";
        return getSession().createQuery(hql, ProdPic.class)
                .setParameter("prodId", prodId).list();
    }

    @Override
    public List<ProdPic> getAllPics() {
        return getSession().createQuery("from ProdPic").list();
    }
}
