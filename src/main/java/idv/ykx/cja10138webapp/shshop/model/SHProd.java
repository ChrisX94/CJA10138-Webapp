package idv.ykx.cja10138webapp.shshop.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SHProd implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer prodId;                     // 二手商品編號（PK）
    private Integer userId;                     // 會員編號（FK）
    private String prodName;                    // 二手商品名稱
    private Integer prodTypeId;                 // 二手商品分類編號（FK）
    private String prodContent;                // 二手商品內容
    private String prodStatusDesc;              // 二手商品敘述
    private Integer prodPrice;                  // 二手商品價格
    private String prodBrand;                   // 商品品牌
    private Integer prodCount;                  // 商品上架數量
    private Integer prodViews;                  // 商品瀏覽次數
    private Integer prodStatus;                 // 商品狀態（0：審核中，1：不通過，2：上架，3：下架）
    private Boolean isSold;                     // 已售出
    private Timestamp prodRegTime;              // 商品上架日期
    private Timestamp updatedTime;              // 最後更新時間



}
