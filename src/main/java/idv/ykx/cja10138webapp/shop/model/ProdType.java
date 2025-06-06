package idv.ykx.cja10138webapp.shop.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="PROD_TYPE")
public class ProdType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROD_TYPE_ID")
    private Integer prodTypeId;

    @Column(name="PROD_TYPE_NAME")
    private String prodTypeName;

    public ProdType() {
        super();
    }

    public ProdType(Integer prodTypeId, String prodTypeName) {
        this.prodTypeId = prodTypeId;
        this.prodTypeName = prodTypeName;
    }

    public Integer getProdTypeId() {
        return prodTypeId;
    }

    public void setProdTypeId(Integer prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public String getProdTypeName() {
        return prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
    }

    @Override
    public String toString() {
        return "ProdType{" +
                "prodTypeId=" + prodTypeId +
                ", prodTypeName='" + prodTypeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdType prodType = (ProdType) o;
        return Objects.equals(prodTypeId, prodType.prodTypeId) && Objects.equals(prodTypeName, prodType.prodTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodTypeId, prodTypeName);
    }
}
