package idv.ykx.cja10138webapp.shshop.dto;

import idv.ykx.cja10138webapp.shshop.model.ShProd;
import idv.ykx.cja10138webapp.shshop.model.ShProdType;
import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ShProdTypeDto {
    private Integer prodTypeId;
    private String prodTypeName;
    private List<ShProd> shProds;

    public ShProdTypeDto() {}

    public ShProdTypeDto(Integer prodTypeId, String prodTypeName) {
        this.prodTypeId = prodTypeId;
        this.prodTypeName = prodTypeName;
    }
    public ShProdTypeDto(ShProdType shProdType) {
        this.prodTypeId = shProdType.getProdTypeId();
        this.prodTypeName = shProdType.getProdTypeName();
        this.shProds = new ArrayList<>();
        for (ShProd prod: shProdType.getShProds()){
            this.shProds.add(prod);
        }
    }


}
