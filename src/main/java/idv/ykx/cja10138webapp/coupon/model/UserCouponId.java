package idv.ykx.cja10138webapp.coupon.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCouponId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private Integer couponId;

    public UserCouponId() {
        super();
    }

    public UserCouponId(Integer userId, Integer couponId) {
        this.userId = userId;
        this.couponId = couponId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    @Override
    public String toString() {
        return "UserCouponId{" +
                "userId=" + userId +
                ", couponId=" + couponId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserCouponId that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(couponId, that.couponId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, couponId);
    }
}
