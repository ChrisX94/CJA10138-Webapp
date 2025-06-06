package idv.ykx.cja10138webapp.shop.controller;

import idv.ykx.cja10138webapp.shop.dao.ProdImgDao;
import idv.ykx.cja10138webapp.shop.dao.daoimpl.ProdImgDaoImpl;
import idv.ykx.cja10138webapp.shop.model.ProdPic;
import idv.ykx.cja10138webapp.shop.service.ProdPicService;
import idv.ykx.cja10138webapp.shop.service.ProdService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/product-img")
public class ImgController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProdPicService service;

    @Override
    public void init() throws ServletException {
        service = new ProdPicService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setCharacterEncoding("UTF-8");

        String productIdStr = req.getParameter("productId").trim();
        String imgIndexStr = req.getParameter("imgIndex").trim();

        //=====================================================
        if (productIdStr != null && imgIndexStr != null) {
            List<ProdPic> list;
            Integer prodId;
            Integer imgIndex = 0;
            try {
                prodId = Integer.parseInt(productIdStr);
                imgIndex = Integer.parseInt(imgIndexStr);
                list = service.getProdPic(prodId);
                if (list == null)  {
                    return;
                }else{
                    byte[] imgData;
                    for(int i=0; i<list.size(); i++) {
                        imgData = (list.get(imgIndex).getProdPic());
                        if (imgData != null) {
                            res.setContentType("image/png");
                            try (OutputStream os = res.getOutputStream()) {
                                os.write(imgData);
                            } catch (IOException e) {
                                res.sendError(HttpServletResponse.SC_NOT_FOUND, "format error");
                                return;
                            }
                        }else{
                            res.sendError(HttpServletResponse.SC_NOT_FOUND, "format error");
                        }
                    }
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

    }
}