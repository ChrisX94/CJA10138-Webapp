package idv.ykx.cja10138webapp.shshop.service;

import com.google.gson.Gson;
import idv.ykx.cja10138webapp.shshop.dto.ShProdDto;
import idv.ykx.cja10138webapp.shshop.dao.ShShopDao;
import idv.ykx.cja10138webapp.shshop.dao.daoimpl.ShShopImpl;
import idv.ykx.cja10138webapp.shshop.dto.ShProdTypeDto;
import idv.ykx.cja10138webapp.shshop.model.ShProd;
import idv.ykx.cja10138webapp.shshop.model.ShProdPic;
import idv.ykx.cja10138webapp.shshop.model.ShProdType;
import idv.ykx.cja10138webapp.user.model.Users;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ShShopService {
    private ShShopDao dao;
    public ShShopService(){
        dao = new ShShopImpl();
    }
//    getProdByUserId
    public String getProdsByUser(Integer userid){
        Gson gson = new Gson();
        List<ShProdDto> dtos = new ArrayList<>();
        List<ShProd> list = dao.getProdByUserId(userid);
        for(ShProd p : list){
            ShProdDto dto = new ShProdDto(p);
            dtos.add(dto);
        }
        return gson.toJson(dtos);

    }

    public ShProd updateProd(ShProd shProd, String prodName, Integer ShProdTypeId, String prodContent,
                             String prodStatusDesc, Integer prodPrice, String prodBrand, byte prodStatus, String[] shProdPicUrl){
        ShProdType shProdType = new ShProdType();
        shProdType.setProdTypeId(ShProdTypeId);
        shProd.setProdType(shProdType);
        shProd.setProdName(prodName);
        shProd.setProdContent(prodContent);
        shProd.setProdStatusDesc(prodStatusDesc);
        shProd.setProdPrice(prodPrice);
        shProd.setProdBrand(prodBrand);
//        shProd.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        shProd.setProdStatus(prodStatus);
        List<ShProdPic> shProdPic = shProdPic(shProdPicUrl,shProd);
        shProd.setProdPics(shProdPic);
        return dao.updateProd(shProd);
    }


    public String createNewProd(Integer userId, String prodName, Integer ShProdTypeId, String prodContent,
                                String prodStatusDesc, Integer prodPrice, String prodBrand, Integer prodCount , byte prodStatus, String[] shProdPicUrl) {
        Gson gson = new Gson();
        Users user = new Users();
        user.setUserId(userId);
        ShProdType shProdType = new ShProdType();
        shProdType.setProdTypeId(ShProdTypeId);
        ShProd shProd = new ShProd();
        shProd.setUser(user);
        shProd.setProdName(prodName);
        shProd.setProdType(shProdType);
        shProd.setProdContent(prodContent);
        shProd.setProdStatusDesc(prodStatusDesc);
        shProd.setProdPrice(prodPrice);
        shProd.setProdBrand(prodBrand);
        shProd.setProdCount(prodCount);
        shProd.setProdViews(0);
        shProd.setProdStatus(prodStatus);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        shProd.setProdRegTime(now);
        shProd.setUpdatedTime(now);
        List<ShProdPic> shProdPic = shProdPic(shProdPicUrl,shProd);
        shProd.setProdPics(shProdPic);
        ShProdDto dto =  new ShProdDto(dao.addShProd(shProd));
        return gson.toJson(dto) ;
    }

    public String getOneById(Integer id){
        Gson gson  = new Gson();
        ShProd shProd = dao.getOneByPK(id);
        ShProdDto dto = new ShProdDto(shProd); // 呼叫建構子轉換
        return gson.toJson(dto);
    }

    public List<ShProd> getAll(){

        return dao.findAll();
    }

    public String getAllJson() {
        Gson gson  = new Gson();
        List<ShProd> list = dao.findAllWithPicsAndType();
        List<ShProdDto> dtoList = new ArrayList<>();
        for (ShProd prod : list) {
            ShProdDto dto = new ShProdDto(prod); // 呼叫建構子轉換
            dtoList.add(dto);
        }
        return gson.toJson(dtoList);
    }

    public String getAllType(){
        Gson gson = new Gson();
        List<ShProdTypeDto> dtoList = new ArrayList<>();
        List<ShProdType> list = dao.findAllTypes();
        for(ShProdType type : list ){
            ShProdTypeDto dto = new ShProdTypeDto(type.getProdTypeId(), type.getProdTypeName() );
            dtoList.add(dto);
        }
        return gson.toJson(dtoList);
    }

    private List<ShProdPic> shProdPic(String[] shProdPicUrl, ShProd shProd) {
        List<ShProdPic> pics = shProd.getProdPics();
        if (pics == null) {
            // 新增時：原 list 為 null，初始化
            pics = new ArrayList<>();
            shProd.setProdPics(pics);
        } else {
            // 更新時：清除舊圖（會觸發 Hibernate orphanRemoval 自動刪除）
            pics.clear();
        }
        // 新增新圖（無論是新增或更新）
        if (shProdPicUrl != null && shProdPicUrl.length > 0) {
            for (String url : shProdPicUrl) {
                ShProdPic newPic = new ShProdPic();
                newPic.setShProd(shProd);
                newPic.setProdPic(url);
                pics.add(newPic);
            }
        }
        return pics;
    }
}
