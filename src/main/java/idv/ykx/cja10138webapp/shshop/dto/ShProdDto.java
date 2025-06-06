package idv.ykx.cja10138webapp.shshop.dto;

import idv.ykx.cja10138webapp.shshop.model.ShProd;
import idv.ykx.cja10138webapp.shshop.model.ShProdPic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShProdDto {
    private Integer prodId;
    private Integer user;
    private String userName;
    private String prodName;
    private Integer prodPrice;
    private String prodBrand;
    private Integer prodCount;
    private String prodTypeName;
    private String prodDesc;
    private String prodContent;
    private byte prodStatus;
    private String  prodStatusStr;
    private List<String> picUrls;

    public ShProdDto(ShProd p) {
        this.prodId = p.getProdId();
        this.user = p.getUser().getUserId();
        this.userName = p.getUser().getUsername();
        this.prodName = p.getProdName();
        this.prodPrice = p.getProdPrice();
        this.prodBrand = p.getProdBrand();
        this.prodCount = p.getProdCount();
        this.prodDesc = p.getProdStatusDesc();
        this.prodContent = p.getProdContent();
        this.prodStatus = p.getProdStatus();
        this.prodStatusStr = getProdStatusStr(prodStatus);
        this.prodTypeName = p.getShProdType().getProdTypeName(); // 假設類別名稱欄位
        this.picUrls = new ArrayList<>();
        for (ShProdPic pic : p.getProdPics()) {
            this.picUrls.add(pic.getProdPic());
        }
    }


    public  String getProdStatusStr(byte prodStatus) {
        String result = "";
        switch (prodStatus) {
            case 0: result = "審核中";
            break;
            case 1: result = "審核不通過";
            break;
            case 2: result = "已上架";
            break;
            case 3: result = "已下架";
            default:
                result = "審核中";
                break;
        }
        return result;
    }
}