package idv.ykx.cja10138webapp;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/test-db")
public class TestDataSourceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            // 初始化 JNDI 查詢環境
            Context ctx = new InitialContext();

            // 查找 DataSource（名稱需和 context.xml 及 web.xml 一致）
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testshakemate");

            // 嘗試取得連線
            try (Connection conn = ds.getConnection()) {
                out.println("✅ 資料庫連線成功！");
                out.println("Connection object: " + conn);
            }

        } catch (Exception e) {
            out.println("❌ 資料庫連線失敗！");
            e.printStackTrace(out); // 印出錯誤堆疊資訊
        }finally {
            out.close();
        }
    }
}
