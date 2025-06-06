package idv.ykx.cja10138webapp.shshop.dao;

import idv.ykx.cja10138webapp.shshop.model.ShProd;
import idv.ykx.cja10138webapp.shshop.model.ShProdType;

import java.util.List;

public interface ShShopDao {
    public List<ShProd> findAll();
    public ShProd getOneByPK(Integer prodId);
    public ShProd addShProd(ShProd shProd);
    public ShProd updateProd(ShProd prod);
    List<ShProd> findAllWithPicsAndType();
    List<ShProdType>findAllTypes();
    public List<ShProd> getProdByUserId(Integer userId);

}
