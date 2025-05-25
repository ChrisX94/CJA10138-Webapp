package idv.ykx.cja10138webapp.shop.model;

import jakarta.enterprise.inject.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="PROD_PIC")
public class ProdPic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROD_PIC_ID")
    private Integer prodPicId;
    @Column(name="PROD_ID")
    private String prodId;
    // 因為 byte[] 會被 hibernate 視為 tinyblob 型別，所以跟DB裡的 longblob 不符，所以用 columnDefinition 定義
    @Column(name = "PROD_PIC", columnDefinition = "longblob")
    private byte[] prodPic;

    public ProdPic() {
        super();
    }

    public ProdPic(Integer prodPicId, String prodId) {
        this.prodPicId = prodPicId;
        this.prodId = prodId;
    }

    public Integer getProdPicId() {
        return prodPicId;
    }

    public void setProdPicId(Integer prodPicId) {
        this.prodPicId = prodPicId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public byte[] getProdPic() {
        return prodPic;
    }

    public void setProdPic(byte[] prodPic) {
        this.prodPic = prodPic;
    }

    @Override
    public String toString() {
        return "ProdPic{" +
                "prodPicId=" + prodPicId +
                ", prodId='" + prodId + '\'' +
                ", prodPic=" + Arrays.toString(prodPic) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdPic prodPic = (ProdPic) o;
        return Objects.equals(prodPicId, prodPic.prodPicId) && Objects.equals(prodId, prodPic.prodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodPicId, prodId);
    }
}
