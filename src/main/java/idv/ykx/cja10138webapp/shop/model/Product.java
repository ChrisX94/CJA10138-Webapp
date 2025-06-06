package idv.ykx.cja10138webapp.shop.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
@Entity
@Table(name= "PROD")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROD_ID")
    private Integer prodId;
    @Column(name="PROD_NAME")
    private String prodName;
    @Column(name="PROD_TYPE_ID")
    private Integer prodTypeId;
    @Column(name="PROD_CONTENT")
    private String prodContent;
    @Column(name="PRODUCT_DESC")
    private String prodDesc;
    @Column(name="PROD_PRICE")
    private Integer prodPrice;
    @Column(name="PROD_BRAND")
    private String prodBrand;
    @Column(name="PROD_SOLD", updatable = false, insertable = false)
    private Integer prodSold;
    @Column(name="PROD_RATE_SUM", updatable = false, insertable = false)
    private Integer prodRateSum;
    @Column(name="PROD_RATE_COUNT_SUM", updatable = false, insertable = false)
    private Integer prodRateCountSum;
    @Column(name="PROD_VIEWS", updatable = false, insertable = false)
    private Integer prodViews;
    @CreationTimestamp
    @Column(name="PROD_CRE_TIME", updatable = false)
    private Timestamp prodCreTime;
    @UpdateTimestamp
    @Column(name="PROD_UPD_TIME")
    private Timestamp prodUpdTime;
    @Column(name="PROD_STATUS")
    private Boolean prodStatus;

    public Product() {
        super();
    };

    public Product(Integer prodId, String prodName, Integer prodTypeId, String prodContent, String prodDesc, Integer prodPrice, String prodBrand, Integer prodSold, Integer prodRateSum, Integer prodRateCountSum, Integer prodViews, Timestamp prodCreTime, Timestamp prodUpdTime, Boolean prodStatus) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodTypeId = prodTypeId;
        this.prodContent = prodContent;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
        this.prodBrand = prodBrand;
        this.prodSold = prodSold;
        this.prodRateSum = prodRateSum;
        this.prodRateCountSum = prodRateCountSum;
        this.prodViews = prodViews;
        this.prodCreTime = prodCreTime;
        this.prodUpdTime = prodUpdTime;
        this.prodStatus = prodStatus;
    }

    //  without arguments for the attribute with default value
    public Product(String prodName, Integer prodTypeId, String prodContent, String prodDesc,
                   Integer prodPrice, String prodBrand, Boolean prodStatus) {
        this.prodName = prodName;
        this.prodTypeId = prodTypeId;
        this.prodContent = prodContent;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
        this.prodBrand = prodBrand;
        this.prodSold = 0;
        this.prodRateSum = 0;
        this.prodRateCountSum = 0;
        this.prodViews = 0;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        this.prodCreTime = now;
        this.prodUpdTime = now;
        this.prodStatus = prodStatus;
    }



    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getProdTypeId() {
        return prodTypeId;
    }

    public void setProdTypeId(Integer prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public String getProdContent() {
        return prodContent;
    }

    public void setProdContent(String prodContent) {
        this.prodContent = prodContent;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String productDesc) {
        this.prodDesc = productDesc;
    }

    public Integer getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdBrand() {
        return prodBrand;
    }

    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand;
    }

    public Integer getProdSold() {
        return prodSold;
    }

    public void setProdSold(Integer prodSold) {
        this.prodSold = prodSold;
    }

    public Integer getProdRateSum() {
        return prodRateSum;
    }

    public void setProdRateSum(Integer prodRateSum) {
        this.prodRateSum = prodRateSum;
    }

    public Integer getProdRateCountSum() {
        return prodRateCountSum;
    }

    public void setProdRateCountSum(Integer prodRateCountSum) {
        this.prodRateCountSum = prodRateCountSum;
    }

    public Integer getProdViews() {
        return prodViews;
    }

    public void setProdViews(Integer prodViews) {
        this.prodViews = prodViews;
    }

    public Timestamp getProdCreTime() {
        return prodCreTime;
    }

    public void setProdCreTime(Timestamp prodCreTime) {
        this.prodCreTime = prodCreTime;
    }

    public Timestamp getProdUpdTime() {
        return prodUpdTime;
    }
    public void setProdUpdTime(Timestamp prodUpdTime) {
        this.prodUpdTime = prodUpdTime;
    }

    public Boolean getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(Boolean prodStatus) {
        this.prodStatus = prodStatus;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", prodTypeId=" + prodTypeId +
                ", prodContent='" + prodContent + '\'' +
                ", prodDesc='" + prodDesc + '\'' +
                ", prodPrice=" + prodPrice +
                ", prodBrand='" + prodBrand + '\'' +
                ", prodSold=" + prodSold +
                ", prodRateSum=" + prodRateSum +
                ", prodRateCountSum=" + prodRateCountSum +
                ", prodViews=" + prodViews +
                ", prodCreTime=" + prodCreTime +
                ", prodUpdTime=" + prodUpdTime +
                ", prodStatus=" + prodStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(prodId, product.prodId) && Objects.equals(prodName, product.prodName) && Objects.equals(prodTypeId, product.prodTypeId) && Objects.equals(prodContent, product.prodContent) && Objects.equals(prodDesc, product.prodDesc) && Objects.equals(prodPrice, product.prodPrice) && Objects.equals(prodBrand, product.prodBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodId, prodName, prodTypeId, prodContent, prodDesc, prodPrice, prodBrand);
    }
}
