package idv.ykx.cja10138webapp.shop.service;

import idv.ykx.cja10138webapp.shop.dao.ProdImgDao;
import idv.ykx.cja10138webapp.shop.dao.daoimpl.ProdImgDaoHibernate;
import idv.ykx.cja10138webapp.shop.dao.daoimpl.ProdImgDaoImpl;
import idv.ykx.cja10138webapp.shop.model.ProdPic;

import java.util.List;

public class ProdPicService {

    ProdImgDao dao;
    public ProdPicService(){
//         dao = new ProdImgDaoImpl();
         dao = new ProdImgDaoHibernate();
    };

    public void uploadProdPic(Integer prodId, byte[] image ) {
        ProdPic prodPic = new ProdPic();
        prodPic.setProdId(prodId);
        prodPic.setProdPic(image);
        dao.uploadPic(prodPic);
    }

    public List<ProdPic> getProdPic(Integer prodId){
        return dao.getProdPicByProdId(prodId);
    }
    public ProdPic getProdPicById(Integer prodPicId){
        return dao.getProdPicById(prodPicId);
    }
    public List<ProdPic> getAllPics(){
        return dao.getAllPics();
    }
}
