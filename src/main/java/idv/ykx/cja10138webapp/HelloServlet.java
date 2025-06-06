package idv.ykx.cja10138webapp;

import idv.ykx.cja10138webapp.shop.dao.ProdDao;
import idv.ykx.cja10138webapp.shop.dao.daoimpl.ProdDaoHibernateImpl;
import idv.ykx.cja10138webapp.shshop.model.ShProd;
import idv.ykx.cja10138webapp.shshop.model.ShProdPic;
import idv.ykx.cja10138webapp.shshop.service.ShShopService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/hello-servlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("中文測試");

//        ProdDao dao = new ProdDaoHibernateImpl();
//        System.out.println(dao.findAll());
//        System.out.println(dao.findAllType());
        ShShopService sss = new ShShopService();
        ShShopService dao = new ShShopService();
        System.out.println("DAO" + dao.getAllType());

        System.out.println("Service"+ sss.getAllType());

//        System.out.println(sss.getAllJson());
//        System.out.println(sss.getAll());
//        System.out.println(sss.getOneById(3));
//        String[] picUrls = {
//                "https://example.com/img1.jpg",
//                "https://example.com/img2.jpg"
//        };
//
//        ShProd newProd = sss.createNewProd(
//                5,                        // userId
//                "aaaaaaaaaaaaaaaaaaaaaaa",               // prodName
//                3,                        // prodTypeId
//                "ada",   // prodContent
//                "9.5 成新",               // prodStatusDesc
//                877,                     // price
//                "aaaaaaaaa",                   // brand
//                (byte) 0,                 // status
//                picUrls                  // 圖片網址陣列
//        );
//        System.out.println(newProd);
//        System.out.println(newProd.getProdId());

        // === 模擬從資料庫撈出原本商品（實務上你應該要用 DAO 去查） ===
//        String shProd =sss.getOneById(1);



        // 模擬商品已有圖片（原圖）
//        List<ShProdPic> existingPics = new ArrayList<>();
//        ShProdPic oldPic = new ShProdPic();
//        oldPic.setProdPic("https://example.com/old.jpg");
//        oldPic.setShProd(shProd);
//        existingPics.add(oldPic);
//        shProd.setProdPics(existingPics);

//        // 印出更新前內容
//        out.println("更新前：");
//        out.println("商品名稱：" + shProd.getProdName());
//        for (ShProdPic pic : shProd.getProdPics()) {
//            out.println("圖片：" + pic.getProdPic());
//        }
//
//        // === 模擬前端傳來的更新資料 ===
//        String[] newPicUrls = {
//                "https://cdn.example.com/pic-new1.jpg",
//                "https://cdn.example.com/pic-new2.jpg"
//        };
//
//        String updatedName = "更新後商品名稱";
//        int updatedTypeId = 2;
//        String updatedContent = "這是更新後的內容";
//        String updatedStatusDesc = "全新";
//        int updatedPrice = 999;
//        String updatedBrand = "更新品牌";
//        byte updatedStatus = 1;
//
//        // === 呼叫你的更新方法 ===
//        ShProd updated = sss.updateProd(
//                shProd,
//                updatedName,
//                updatedTypeId,
//                updatedContent,
//                updatedStatusDesc,
//                updatedPrice,
//                updatedBrand,
//                updatedStatus,
//                newPicUrls
//        );
//
//        // === 印出更新後結果 ===
//        out.println("\n更新後：");
//        out.println("商品名稱：" + updated.getProdName());
//        out.println("商品價格：" + updated.getProdPrice());
//        out.println("商品狀態說明：" + updated.getProdStatusDesc());
//        out.println("圖片清單：");
//        for (ShProdPic pic : updated.getProdPics()) {
//            out.println("圖片：" + pic.getProdPic());
//        }
    }




    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
