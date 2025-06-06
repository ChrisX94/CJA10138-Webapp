package idv.ykx.cja10138webapp.coupon.model;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "USER_COUPON")
public class UserCoupon implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserCouponId id;

    @Column(name = "USED_FLAG", nullable = false)
    private Integer usedFlag; // 0: 未使用, 1: 已使用, 2: 失效

    @Column(name = "REDEEM_DATE")
    private Timestamp redeemDate;

    public UserCoupon() {
        super();
    }

    public UserCoupon(UserCouponId id, Integer usedFlag, Timestamp redeemDate) {
        this.id = id;
        this.usedFlag = usedFlag;
        this.redeemDate = redeemDate;
    }

    public UserCouponId getId() {
        return id;
    }

    public void setId(UserCouponId id) {
        this.id = id;
    }

    public Integer getUsedFlag() {
        return usedFlag;
    }

    public void setUsedFlag(Integer usedFlag) {
        this.usedFlag = usedFlag;
    }

    public Timestamp getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(Timestamp redeemDate) {
        this.redeemDate = redeemDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserCoupon that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(usedFlag, that.usedFlag) && Objects.equals(redeemDate, that.redeemDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usedFlag, redeemDate);
    }
}
