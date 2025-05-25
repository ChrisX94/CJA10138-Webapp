package idv.ykx.cja10138webapp.shop.dao.daoimpl;

import idv.ykx.cja10138webapp.shop.dao.ProdDao;
import idv.ykx.cja10138webapp.shop.model.ProdType;
import idv.ykx.cja10138webapp.shop.model.Product;
import idv.ykx.cja10138webapp.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdDoaJDBCImpl implements ProdDao {
    private static final String GET_ALL = "SELECT * FROM prod";
    private static final String GET_BY_ID = "SELECT * FROM prod WHERE PROD_ID = ?";
    private static final String CREATE = "INSERT INTO PROD (" +
            "PROD_NAME, PROD_TYPE_ID, PROD_CONTENT, PRODUCT_DESC," +
            "PROD_PRICE, PROD_BRAND, PROD_CRE_TIME, PROD_UPD_TIME, PROD_STATUS) VALUES " +
            "(?,?,?,?,?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,?)";

    static {
        try {
            Class.forName(JDBCUtil.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer productId){

    }

    public List<ProdType> findAllType(){
        return null;
    }

    public void update(Product product){

    }
    @Override
    public void addProduct(Product product){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            conn = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASS);
            pstmt = conn.prepareStatement(CREATE);
            pstmt.setString(1, product.getProdName());
            pstmt.setInt(2, product.getProdTypeId());
            pstmt.setString(3, product.getProdContent());
            pstmt.setString(4, product.getProdDesc());
            pstmt.setInt(5, product.getProdPrice());
            pstmt.setString(6, product.getProdBrand());
            pstmt.setBoolean(7, product.getProdStatus());
            pstmt.executeUpdate();

            System.out.println("Create new Product Successfully");
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
            conn = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASS);
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
                product.setProdCreTime(new java.sql.Timestamp(rs.getTimestamp("PROD_CRE_TIME").getTime()));
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
            conn = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASS);
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
                product.setProdCreTime(new java.sql.Timestamp(rs.getTimestamp("PROD_CRE_TIME").getTime()));
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

    private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
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
