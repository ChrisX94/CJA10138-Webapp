package idv.ykx.cja10138webapp.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter implements Filter {
    private FilterConfig config;
    @Override
    public void init(FilterConfig config){
    }

    @Override
    public void destroy(){
        config = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException{
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        Object account = session.getAttribute("account");
        if(account == null){
            session.setAttribute("location", req.getRequestURI());
            res.sendRedirect(req.getContextPath()+ "/login/login.jsp");
            return;
        }else{
            chain.doFilter(request, response);
        }

    }

}
