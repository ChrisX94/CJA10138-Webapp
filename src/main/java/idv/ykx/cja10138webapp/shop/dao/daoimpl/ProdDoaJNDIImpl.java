package idv.ykx.cja10138webapp.shop.dao.daoimpl;

import idv.ykx.cja10138webapp.shop.dao.ProdDao;
import idv.ykx.cja10138webapp.shop.model.ProdType;
import idv.ykx.cja10138webapp.shop.model.Product;
import idv.ykx.cja10138webapp.util.ConnectionPool;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdDoaJNDIImpl implements ProdDao {
    private static final String GET_ALL = "SELECT * FROM PROD";
    private static final String GET_BY_ID = "SELECT * FROM prod WHERE PROD_ID = ?;";
    private static final String CREATE = "INSERT INTO PROD (" +
            "PROD_NAME, PROD_TYPE_ID, PROD_CONTENT, PRODUCT_DESC," +
            "PROD_PRICE, PROD_BRAND, PROD_CRE_TIME, PROD_UPD_TIME, PROD_STATUS) VALUES " +
            "(?,?,?,?,?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?);";
    private static final String UPDATE = "UPDATE PROD SET" +
            " PROD_NAME=?, PROD_TYPE_ID=?, PROD_CONTENT=?, PRODUCT_DESC=?," +
            "PROD_PRICE=?, PROD_BRAND=?,PROD_UPD_TIME= CURRENT_TIMESTAMP, PROD_STATUS=?" +
            " WHERE PROD_ID = ? ;";
    private static final String DELETE = "DELETE FROM PROD WHERE PROD_ID = ?;";
    private static final String GET_TYPE = "SELECT * FROM PROD_TYPE WHERE PROD_TYPE_ID = ?;";

    // Product Type
    private static final String GET_ALL_TYPE = "SELECT * FROM prod_type";

    private static DataSource ds= new ConnectionPool().getConnPool();

    @Override
    public void update(Product product){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = ds.getConnection();
            String test = "SET FOREIGN_KEY_CHECKS = 0;"; // only for testing
            pstmt = conn.prepareStatement(test);  // only for testing
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(UPDATE);
            pstmt.setString(1, product.getProdName());
            pstmt.setInt(2, product.getProdTypeId());
            pstmt.setString(3, product.getProdContent());
            pstmt.setString(4, product.getProdDesc());
            pstmt.setInt(5, product.getProdPrice());
            pstmt.setString(6, product.getProdBrand());
            pstmt.setBoolean(7, product.getProdStatus());
            pstmt.setInt(8, product.getProdId());
            pstmt.executeUpdate();

            System.out.println("Updated Successfully");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void addProduct(Product product){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(CREATE);
            pstmt.setString(1, product.getProdName());
            pstmt.setInt(2, product.getProdTypeId());
            pstmt.setString(3, product.getProdContent());
            pstmt.setString(4, product.getProdDesc());
            pstmt.setInt(5, product.getProdPrice());
            pstmt.setString(6, product.getProdBrand());
            pstmt.setBoolean(7, product.getProdStatus());
            pstmt.executeUpdate();

            System.out.println("Updated Successfully");
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            closeResources(conn, pstmt, rs);
        }
    }

    @Override
    public Product findByPrimaryKey(Integer productId) {
        Product product = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(GET_BY_ID);
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                product = new Product();
                product.setProdId(rs.getInt("PROD_ID"));
                product.setProdName(rs.getString("PROD_NAME"));
                product.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
                product.setProdContent(rs.getString("PROD_CONTENT"));
                product.setProdDesc(rs.getString("PRODUCT_DESC"));
                product.setProdPrice(rs.getInt("PROD_PRICE"));
                product.setProdBrand(rs.getString("PROD_BRAND"));
                product.setProdSold(rs.getInt("PROD_SOLD"));
                product.setProdRateSum(rs.getInt("PROD_RATE_SUM"));
                product.setProdRateCountSum(rs.getInt("PROD_RATE_COUNT_SUM"));
                product.setProdViews(rs.getInt("PROD_VIEWS"));
                product.setProdCreTime(new Timestamp(rs.getTimestamp("PROD_CRE_TIME").getTime()));
                product.setProdUpdTime(new java.sql.Timestamp(rs.getTimestamp("PROD_UPD_TIME").getTime()));
                product.setProdStatus(rs.getBoolean("PROD_STATUS"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<Product>();
        Product product = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(GET_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                product = new Product();
                product.setProdId(rs.getInt("PROD_ID"));
                product.setProdName(rs.getString("PROD_NAME"));
                product.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
                product.setProdContent(rs.getString("PROD_CONTENT"));
                product.setProdDesc(rs.getString("PRODUCT_DESC"));
                product.setProdPrice(rs.getInt("PROD_PRICE"));
                product.setProdBrand(rs.getString("PROD_BRAND"));
                product.setProdSold(rs.getInt("PROD_SOLD"));
                product.setProdRateSum(rs.getInt("PROD_RATE_SUM"));
                product.setProdRateCountSum(rs.getInt("PROD_RATE_COUNT_SUM"));
                product.setProdViews(rs.getInt("PROD_VIEWS"));
                product.setProdCreTime(new Timestamp(rs.getTimestamp("PROD_CRE_TIME").getTime()));
                product.setProdUpdTime(new java.sql.Timestamp(rs.getTimestamp("PROD_UPD_TIME").getTime()));
                product.setProdStatus(rs.getBoolean("PROD_STATUS"));
                products.add(product);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }

        return products;
    }

    public ProdType getOneProdType(Integer prodTypeId){
        ProdType prodType = new ProdType();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(GET_TYPE);
            pstmt.setInt(1, prodTypeId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                prodType.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
                prodType.setProdTypeName(rs.getString("PROD_TYPE_NAME"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            closeResources(conn, pstmt, rs);
        }
        return prodType;
    }

    public List<ProdType> findAllType(){
        List<ProdType> prodTypes = new ArrayList<ProdType>();
        ProdType prodTpye = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(GET_ALL_TYPE);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                prodTpye = new ProdType();
                prodTpye.setProdTypeId(rs.getInt("PROD_TYPE_ID"));
                prodTpye.setProdTypeName(rs.getString("PROD_TYPE_NAME"));
                prodTypes.add(prodTpye);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return prodTypes;

    }


    public void delete(Integer productId){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String test = "SET FOREIGN_KEY_CHECKS = 0;"; // only for testing
            pstmt = conn.prepareStatement(test);  // only for testing
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement(DELETE);
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();

            System.out.println("Product deleted successfully");
        }catch(SQLException e) {
            e.printStackTrace();
        }finally{
            closeResources(conn, pstmt);
        }

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
