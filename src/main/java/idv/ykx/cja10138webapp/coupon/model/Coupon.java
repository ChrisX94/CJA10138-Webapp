package idv.ykx.cja10138webapp.coupon.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "COUPON")
public class Coupon implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPON_ID")
    private Integer couponId;

    @Column(name = "COUPON_CODE", length = 50, nullable = false)
    private String couponCode;

    @Column(name = "COUPON_CONTENT", length = 800, nullable = false)
    private String couponContent;

    @Column(name = "SPEND_OVER", nullable = false)
    private Integer spendOver;

    @Column(name = "COUPON_START", nullable = false)
    private Timestamp couponStart;

    @Column(name = "COUPON_END", nullable = false)
    private Timestamp couponEnd;

    @Column(name = "DISCOUNT_AMOUNT", nullable = false)
    private Integer discountAmount;

    public Coupon() {
        super();
    }

    public Coupon(Integer couponId, String couponCode, String couponContent, Integer spendOver, Timestamp couponStart, Timestamp couponEnd, Integer discountAmount) {
        this.couponId = couponId;
        this.couponCode = couponCode;
        this.couponContent = couponContent;
        this.spendOver = spendOver;
        this.couponStart = couponStart;
        this.couponEnd = couponEnd;
        this.discountAmount = discountAmount;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponContent() {
        return couponContent;
    }

    public void setCouponContent(String couponContent) {
        this.couponContent = couponContent;
    }

    public Integer getSpendOver() {
        return spendOver;
    }

    public void setSpendOver(Integer spendOver) {
        this.spendOver = spendOver;
    }

    public Timestamp getCouponStart() {
        return couponStart;
    }

    public void setCouponStart(Timestamp couponStart) {
        this.couponStart = couponStart;
    }

    public Timestamp getCouponEnd() {
        return couponEnd;
    }

    public void setCouponEnd(Timestamp couponEnd) {
        this.couponEnd = couponEnd;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "couponId=" + couponId +
                ", couponCode='" + couponCode + '\'' +
                ", couponContent='" + couponContent + '\'' +
                ", spendOver=" + spendOver +
                ", couponStart=" + couponStart +
                ", couponEnd=" + couponEnd +
                ", discountAmount=" + discountAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coupon coupon)) return false;
        return Objects.equals(couponId, coupon.couponId) && Objects.equals(couponCode, coupon.couponCode) && Objects.equals(couponContent, coupon.couponContent) && Objects.equals(spendOver, coupon.spendOver) && Objects.equals(couponStart, coupon.couponStart) && Objects.equals(couponEnd, coupon.couponEnd) && Objects.equals(discountAmount, coupon.discountAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couponId, couponCode, couponContent, spendOver, couponStart, couponEnd, discountAmount);
    }
}
