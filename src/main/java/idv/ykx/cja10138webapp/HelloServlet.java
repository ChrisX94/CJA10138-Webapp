package idv.ykx.cja10138webapp;

import java.io.*;
import java.util.List;

import idv.ykx.cja10138webapp.shop.dao.daoimpl.ProdDaoHibernateImpl;
import idv.ykx.cja10138webapp.shop.dao.daoimpl.ProdDoaJDBCImpl;
import idv.ykx.cja10138webapp.shop.dao.daoimpl.ProdDoaJNDIImpl;
import idv.ykx.cja10138webapp.shop.model.ProdType;
import idv.ykx.cja10138webapp.shop.model.Product;
import idv.ykx.cja10138webapp.shop.service.ProdService;
import idv.ykx.cja10138webapp.util.HibernateUtil;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello Servlet!";
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");

///////////////////////////JDBC Test//////////////////////////////////////////
//        ProdDoaJDBCImpl a = new ProdDoaJDBCImpl();
//        ProdDoaJNDIImpl a = new ProdDoaJNDIImpl();
//        Product pd = new Product();
//        pd.setProdName("name");
//        pd.setProdTypeId(1);
//        pd.setProdContent("Content");
//        pd.setProdDesc("Desc");
//        pd.setProdBrand("Brand");
//        pd.setProdPrice(87);
//        pd.setProdStatus(false);
//        a.addProduct(pd);
//
//        List<Product> b =  a.findAll();
//        for(Product p : b){
//            System.out.println(p);
//        }
//
//        a.delete(17);
//
//        b =  a.findAll();
//        for(Product p : b){
//            System.out.println(p);
//        }

//        Product pdup = new Product();
//        pdup.setProdId(14);
//        pdup.setProdName("updateName");
//        pdup.setProdTypeId(1);
//        pdup.setProdContent("Content");
//        pdup.setProdDesc("Desc");
//        pdup.setProdBrand("Brand");
//        pdup.setProdPrice(87);
//        pdup.setProdStatus(true);
//        a.update(pdup);


//        List<ProdType> pt = a.findAllType();
//        for(ProdType p : pt){
//            System.out.println(p);
//        }

//        ProdService ps = new ProdService();
//        ps.updateProduct(14,"TestProdServerUpdate",4 , "0487", "0487" ,987, "New Brand", true);

///////////////////////////JDBC Test End//////////////////////////////////////////

///////////////////////////Hibernate Test//////////////////////////////////////////
//        ProdDaoHibernateImpl prodDao = new ProdDaoHibernateImpl();
//        Product prod = prodDao.findByPrimaryKey(1);
//        System.out.println(prod);


///////////////////////////Hibernate Test End//////////////////////////////////////////
        // Hello
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}