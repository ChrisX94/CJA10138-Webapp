package idv.ykx.cja10138webapp.shop.service;

import idv.ykx.cja10138webapp.shop.dao.ProdDao;
import idv.ykx.cja10138webapp.shop.dao.daoimpl.ProdDaoHibernateImpl;
import idv.ykx.cja10138webapp.shop.dao.daoimpl.ProdDoaJNDIImpl;
import idv.ykx.cja10138webapp.shop.model.ProdType;
import idv.ykx.cja10138webapp.shop.model.Product;

import java.sql.Timestamp;
import java.util.List;

public class ProdService {
    private ProdDao prodDao;

    // Constructor, new ProdDoaJDBCImpl instance once new ProdService instance
    public ProdService() {
        prodDao = new ProdDaoHibernateImpl();
//        prodDao = new ProdDoaJNDIImpl();
    }

    // Update
    public Product updateProduct(Integer prodId,String prodName,Integer prodTypeId , String prodContent,
                              String prodDesc ,Integer prodPrice, String prodBrand, Boolean prodStatus){
        Product product = new Product();
        product.setProdId(prodId);
        product.setProdName(prodName);
        product.setProdTypeId(prodTypeId);
        product.setProdContent(prodContent);
        product.setProdDesc(prodDesc);
        product.setProdPrice(prodPrice);
        product.setProdBrand(prodBrand);
        product.setProdStatus(prodStatus);
        prodDao.update(product);
        System.out.println(prodDao.findByPrimaryKey(prodId));

        return prodDao.findByPrimaryKey(prodId);
    }

    // Create new product
    public Product addProduct(String prodName,Integer prodTypeId , String prodContent,
                              String prodDesc ,Integer prodPrice, String prodBrand, Boolean prodStatus) {

        Product prod = new Product(prodName, prodTypeId, prodContent, prodDesc, prodPrice, prodBrand, prodStatus);
//        Product prod = new Product();
//        prod.setProdName(prodName);
//        prod.setProdTypeId(prodTypeId);
//        prod.setProdContent(prodContent);
//        prod.setProdDesc(prodDesc);
//        prod.setProdPrice(prodPrice);
//        prod.setProdBrand(prodBrand);
//        Timestamp now = new Timestamp(System.currentTimeMillis());
//        prod.setProdRateSum(0);
//        prod.setProdRateCountSum(0);
//        prod.setProdCreTime(now);
//        prod.setProdUpdTime(now);
//        prod.setProdStatus(prodStatus);
        return prodDao.addProduct(prod);
    }

    // get one by PK
    public Product getOneProduct(Integer prodId){
            return prodDao.findByPrimaryKey(prodId);
        }

    // Select All
    public List<Product> getAllProd(){
        return prodDao.findAll();
    }

    // Get All Product Type
    public List<ProdType> getAllTypes(){
        return prodDao.findAllType();
    }

    // get one Product Type
    public ProdType getProdType(int prodTypeId){
        return prodDao.getOneProdType(prodTypeId);
    }

    // Delete
    public void deleteProduct(Integer prodId){
        prodDao.delete(prodId);
    }

}
