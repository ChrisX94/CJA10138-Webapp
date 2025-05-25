package idv.ykx.cja10138webapp.shop.controller;

import idv.ykx.cja10138webapp.shop.dao.daoimpl.ProdDoaJDBCImpl;
import idv.ykx.cja10138webapp.shop.model.ProdType;
import idv.ykx.cja10138webapp.shop.model.Product;
import idv.ykx.cja10138webapp.shop.service.ProdService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.*;
import java.util.*;

import jakarta.servlet.http.*;

//@WebServlet(name = "shopController", value = "/shopController")
public class ShopController extends HttpServlet {
    ProdService prodService;

    public void init() {
        prodService = new ProdService();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
        doPost(req, res);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action"); // get parameter from jsp (the action) and save it in request
        switch (action) {
            case "getOne_For_Display": getOneForDisplay(req, res); // if the parameter from action is "getOne_For_Display"
             break;
            case "insert": createNewProduct(req, res); // if the parameter from action is "insert"
                break;
            case "getOne_For_Update": getOne_For_Update(req, res);
                break;
            case "update" : update (req, res); // get action: "update" from update_page.jsp" and also the parameters
                break;
            case "delete" : delete(req, res);
                break;
            default:
                RequestDispatcher rd = req.getRequestDispatcher("/shop/index.jsp");
                rd.forward(req, res);
        }
    }

    /* **************************  Get One for Display  ***************************  */
    void getOneForDisplay(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            Map<String, String> errors = new LinkedHashMap<String, String>(); // new a LinkedHashMap to save error messages
            req.setAttribute("errorMsg", errors); // save it in request
            /*get parameter from frontend, and check the format of parameters */
            String str = req.getParameter("prodId"); // get prodId from <input type="text" name="prodId">
            if (str == null || (str.trim()).isEmpty()) { // if it's null OR got empty string
                errors.put("prodId", "請輸入商品編號"); // save error message in LinkedHashMap
            }
            if (!errors.isEmpty()) { // if there is an error message in errors then stop
                RequestDispatcher errorMsg = req.getRequestDispatcher("/shop/product_page.jsp"); // set forward path
                errorMsg.forward(req, res); // send out error msg
                return;  // stop the process
            }

            Integer prodId = null;
            try {
                prodId = Integer.valueOf(str); // casting string to Integer
            } catch (IllegalArgumentException e) {
                errors.put("prodId", "Input format error"); // if exception occurred then add it to errors
            }
            if (!errors.isEmpty()) { // if there is an error message in errors then stop
                RequestDispatcher errorMsg = req.getRequestDispatcher("/shop/product_page.jsp"); // set forward path
                errorMsg.forward(req, res); // send out error msg
                return;  // stop the process
            }
            /* Operating with BD */
            Product product = prodService.getOneProduct(prodId);
            ProdType prodType = prodService.getProdType(prodId);
            if (product == null) { // if the product is not existed then
                errors.put("prodId", "No such product ID"); // add errorMsg to errors
            }
            if (!errors.isEmpty()) { // if there is an error message in errors then stop
                RequestDispatcher errorMsg = req.getRequestDispatcher("/shop/product_page.jsp"); // set forward path
                errorMsg.forward(req, res); // send out error msg
                return;  // stop the process
            }
            /* Query is done, forward the result */
            req.setAttribute("product", product); // set the product obj in request
            req.setAttribute("prodType", prodType);
            String url = "/shop/listOneProd.jsp";
            RequestDispatcher successMsg = req.getRequestDispatcher(url);
            successMsg.forward(req, res);
    }

    /* **************************  Insert  ***************************  */
    void createNewProduct (HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
            Map<String, String> errors = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errors);
            /*get parameter from frontend, and check the format of parameters */
            String prodName = req.getParameter("prodName");
            String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$"; // regx Chinese, number and _ , length is between 1-30
            if (prodName == null || prodName.isEmpty()) { // check null or blank
                errors.put("prodName", "商品名稱不能空白");
            } else if (!prodName.trim().matches(prodNameReg)) { // check format
                errors.put("prodName", "商品名稱只能是中、英文和_，長度介於1到30之間");
            }
            // prodType is using list no need for check
            Integer prodTypeId = Integer.parseInt(req.getParameter("prodTypeId"));

            String prodContent = req.getParameter("prodContent");
            if (prodContent == null || prodContent.isEmpty()) {
                errors.put("prodContent", "商品內容不能空白");
            }
            String prodDesc = req.getParameter("prodDesc");
            if (prodDesc == null || prodDesc.isEmpty()) {
                errors.put("prodDesc", "商品敘述不能空白");
            }
            Integer prodPrice = null;
            try {
                prodPrice = Integer.valueOf(req.getParameter("prodPrice").trim());
                if (prodPrice <= 0) {
                    errors.put("prodPrice", "商品價格不能小於0");
                }
            } catch (NumberFormatException e) {
                errors.put("prodPrice", "商品價格不能空白，必須是整數");
            }
            String prodBrand = req.getParameter("prodBrand");
            if (prodBrand == null || prodBrand.isEmpty()) {
                errors.put("prodBrand", "商品內容不能空白");
            }
            // prodStatus is using list no need for check
            Boolean prodStatus = Boolean.parseBoolean(req.getParameter("prodStatus"));

            if (!errors.isEmpty()) {
                RequestDispatcher errorMsg = req.getRequestDispatcher("/shop/addProd.jsp"); // set forward path
                errorMsg.forward(req, res);
                return;
            }
            /* Operating with BD */
            // send the values to addProduct in prodService
            prodService.addProduct(prodName, prodTypeId, prodContent, prodDesc, prodPrice, prodBrand, prodStatus);

            /* Query is done, forward the result */
            req.setAttribute("success", "新增成功");
            String url = "/shop/addProd.jsp";
            RequestDispatcher successMsg = req.getRequestDispatcher(url);
            successMsg.forward(req, res);

    }

    /* **************************  Update  ***************************  */
    // update has 2 parts
    // part1 one of them is to get the product obj then present it to update_page.jsp
    void getOne_For_Update (HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
            Map<String, String> errors = new LinkedHashMap<String, String>();
            req.setAttribute("errors", errors);
            /*get parameter from frontend, and check the format of parameters */
            Integer ProdId = Integer.valueOf(req.getParameter("prodId")); // no need for check the parameter format since it's the parameter is provided by getALL()
            /* Operating with BD */
            Product prod = prodService.getOneProduct(ProdId);
            /* Query is done, forward the result */
            String param = "?prodId=" + prod.getProdId()
                    + "&prodName=" + prod.getProdName()
                    + "&prodTypeId=" + prod.getProdTypeId()
                    + "&prodContent=" + prod.getProdContent()
                    + "&prodDesc=" + prod.getProdDesc()
                    + "&prodPrice=" + prod.getProdPrice()
                    + "&prodBrand=" + prod.getProdBrand()
                    + "&prodStatus=" + prod.getProdStatus();
            String url = "/shop/update_page.jsp" + param;
            RequestDispatcher prodInfo = req.getRequestDispatcher(url);
            prodInfo.forward(req, res);
    }
    // part2 get the update parameter from update_page.jsp and update DB
    void update (HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {

            Map<String, String> errors = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errors);
            /*get parameter from frontend, and check the format of parameters */
            Integer prodId = Integer.valueOf(req.getParameter("prodId"));
            String prodName = req.getParameter("prodName");
            String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$"; // regx Chinese, number and _ , length is between 1-30
            if (prodName == null || prodName.isEmpty()) { // check null or blank
                errors.put("prodName", "商品名稱不能空白");
            } else if (!prodName.trim().matches(prodNameReg)) { // check format
                errors.put("prodName", "商品名稱只能是中、英文和_，長度介於1到30之間");
            }
            // prodType is using list no need for check
            Integer prodTypeId = Integer.parseInt(req.getParameter("prodTypeId"));

            String prodContent = req.getParameter("prodContent");
            if (prodContent == null || prodContent.isEmpty()) {
                errors.put("prodContent", "商品內容不能空白");
            }
            String prodDesc = req.getParameter("prodDesc");
            if (prodDesc == null || prodDesc.isEmpty()) {
                errors.put("prodDesc", "商品敘述不能空白");
            }
            Integer prodPrice = null;
            try {
                prodPrice = Integer.valueOf(req.getParameter("prodPrice").trim());
                if (prodPrice <= 0) {
                    errors.put("prodPrice", "商品價格不能小於0");
                }
            } catch (NumberFormatException e) {
                errors.put("prodPrice", "商品價格不能空白，必須是整數");
            }
            String prodBrand = req.getParameter("prodBrand");
            if (prodBrand == null || prodBrand.isEmpty()) {
                errors.put("prodBrand", "商品內容不能空白");
            }
            // prodStatus is using list no need for check
            Boolean prodStatus = Boolean.parseBoolean(req.getParameter("prodStatus"));

            if (!errors.isEmpty()) {
                RequestDispatcher errorMsgs = req.getRequestDispatcher("/shop/update_page.jsp"); // set forward path
                errorMsgs.forward(req, res);
                return;
            }
            /* Operating with BD */
            Product prod = prodService.updateProduct(prodId, prodName, prodTypeId, prodContent, prodDesc, prodPrice, prodBrand, prodStatus);
            ProdType prodType = prodService.getProdType(prodId);
            /* Query is done, forward the result */
            req.setAttribute("product", prod);
             req.setAttribute("prodType", prodType);
            String url = "/shop/listOneProd.jsp";
            RequestDispatcher successMsg = req.getRequestDispatcher(url);
            successMsg.forward(req, res);
    }

    /* **************************  delete  ***************************  */
    void delete (HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
        Map<String, String> errors = new LinkedHashMap<String, String>();
        req.setAttribute("errorMsgs", errors);
        /*get parameter from frontend, and check the format of parameters */
        Integer prodId = Integer.valueOf(req.getParameter("prodId"));
        prodService.deleteProduct(prodId);
        req.setAttribute("success", "Product ID: "+ prodId + "has been deleted");
        String url = "/shop/productAll.jsp";
        RequestDispatcher successMsg = req.getRequestDispatcher(url);
        successMsg.forward(req, res);
    }


    public void destroy() {
        System.out.println("destroy executed");
    }
}