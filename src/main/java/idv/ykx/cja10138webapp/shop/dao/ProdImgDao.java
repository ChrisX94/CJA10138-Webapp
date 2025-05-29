package idv.ykx.cja10138webapp.shop.dao;

import idv.ykx.cja10138webapp.shop.model.ProdPic;

import java.util.List;

public interface ProdImgDao {

    public List<ProdPic> getProdPicByProdId(Integer prodId);
    public List<ProdPic> getAllPics();
    public ProdPic getProdPicById(Integer PROD_PIC_ID);
}
