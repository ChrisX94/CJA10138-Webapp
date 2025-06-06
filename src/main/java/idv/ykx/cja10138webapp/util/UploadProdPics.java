package idv.ykx.cja10138webapp.util;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadProdPics {
    private static final String INSERT_BLOB = "INSERT INTO PROD_PIC(PROD_ID, PROD_PIC) VALUES(?, ?);" ;

    private static DataSource ds = null;

    public static void main(String[] args){
        String testFilePath = "src/main/resources/imgs";
        File file = new File(testFilePath);
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        Integer prefix = null;
        String filePath = null;
        for(String fileName : file.list()){
            filePath = (testFilePath + "/" + fileName);
            prefix = Integer.valueOf(fileName.substring(0,2));
            System.out.println(prefix);
            insertBlob(filePath, prefix);


        }





    }

    public static byte[] getPictureByteArray(String path) throws IOException {
        FileInputStream fis = null;
        byte[] buffer =null;
        try {
            fis = new FileInputStream(path);
            buffer = fis.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fis != null){
                fis.close();
            }
        }
        return buffer;

    }
    public static void insertBlob(String filePath, int prodId){
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            byte[] pic = getPictureByteArray(filePath);
//            conn = ds.getConnection();
            conn = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASS);
            stmt = conn.prepareStatement(INSERT_BLOB);
            stmt.setInt(1,prodId);
            stmt.setBytes(2,pic);
            stmt.executeUpdate();

            System.out.println("Picture Uploaded Successfully");
        }catch(IOException e){
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static {
        try {
            Class.forName(JDBCUtil.DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    static{
//        try {
//            Context ctx = new InitialContext();
//            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testshakemate");
//        } catch (NamingException e) {
//            throw new RuntimeException(e);
//        }
//    }







}
