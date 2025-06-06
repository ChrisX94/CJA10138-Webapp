package idv.ykx.cja10138webapp.shop.dao.daoimpl;

import idv.ykx.cja10138webapp.shop.dao.ProdImgDao;
import idv.ykx.cja10138webapp.shop.model.ProdPic;
import idv.ykx.cja10138webapp.util.ConnectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdImgDaoImpl implements ProdImgDao {
    private static final String GET_ALL_PICS = "SELECT * FROM PROD_PIC";
    private static final String GET_PIC_BY_ID = "SELECT * FROM PROD_PIC WHERE PROD_PIC_ID = ?";
    private static final String GET_PIC_BY_PROD_ID = "SELECT * FROM PROD_PIC WHERE PROD_ID = ?";
    private static final String UPLOAD_PIC = "INSERT INTO PROD_PIC (PROD_ID, PROD_PIC) VALUES(?, ?);";

    private static DataSource ds = new ConnectionPool().getConnPool();;

    public void uploadPic(ProdPic prodPic){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = ds.getConnection();
            stmt = conn.prepareStatement(UPLOAD_PIC);
            stmt.setInt(1, prodPic.getProdId());
            stmt.setBytes(2, prodPic.getProdPic());
            stmt.executeUpdate();
            System.out.println("Uploaded Pic Successfully");
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            closeResources(conn, stmt);
        }
    }

    public List<ProdPic> getProdPicByProdId(Integer prodId){
        List<ProdPic> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = ds.getConnection();
            stmt = conn.prepareStatement(GET_PIC_BY_PROD_ID);
            stmt.setInt(1, prodId);
            rs = stmt.executeQuery();
            ProdPic prodPic;
            while(rs.next()){
                prodPic = new ProdPic();
                prodPic.setProdPicId(rs.getInt("PROD_PIC_ID"));
                prodPic.setProdId(rs.getInt("PROD_ID"));
                prodPic.setProdPic(rs.getBytes("PROD_PIC"));
                list.add(prodPic);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            closeResources(conn, stmt, rs);
        }
        return list;
    }

    public ProdPic getProdPicById(Integer prodPicId) {
        ProdPic prodPic = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            stmt = conn.prepareStatement(GET_PIC_BY_ID);
            stmt.setInt(1, prodPicId);
            rs = stmt.executeQuery();

            while(rs.next()){
                prodPic = new ProdPic();
                prodPic.setProdPicId(rs.getInt("PROD_PIC_ID"));
                prodPic.setProdId(rs.getInt("PROD_ID"));
                prodPic.setProdPic(rs.getBytes("PROD_PIC"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            closeResources(conn, stmt, rs);
        }

        return prodPic;
    }

    public List<ProdPic> getAllPics(){
        List<ProdPic> list = new ArrayList<ProdPic>();
        ProdPic prodPic = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = ds.getConnection();
            stmt = conn.prepareStatement(GET_ALL_PICS);
            rs = stmt.executeQuery();
            while(rs.next()){
                prodPic = new ProdPic();
                prodPic.setProdPicId(rs.getInt("PROD_PIC_ID"));
                prodPic.setProdId(rs.getInt("PROD_ID"));
                prodPic.setProdPic(rs.getBytes("PROD_PIC"));
                list.add(prodPic);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            closeResources(conn, stmt, rs);
        }
        return list;
    }



    private void closeResources(Connection con, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private void closeResources(Connection con, PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

}
