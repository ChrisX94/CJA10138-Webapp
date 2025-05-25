package idv.ykx.cja10138webapp.shop.dao;

import idv.ykx.cja10138webapp.shop.model.*;

import java.util.List;

public interface ProdDao {
    public void update(Product product);
    public void addProduct(Product product);
    public List<Product> findAll();
    public Product findByPrimaryKey(Integer productId);
    public void delete(Integer productId);


    // Product Type
    public List<ProdType> findAllType();

}
