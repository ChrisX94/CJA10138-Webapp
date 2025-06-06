package idv.ykx.cja10138webapp.filter;


import idv.ykx.cja10138webapp.util.HibernateUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.hibernate.SessionFactory;

import java.io.IOException;
//@WebFilter(urlPatterns = { "/*" })
public class OpenSessionViewFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try{
            System.out.println("Session opened by Filter");
            factory.getCurrentSession().beginTransaction();
            chain.doFilter(req, res);
            factory.getCurrentSession().getTransaction().commit();
        }catch(Exception e){
            factory.getCurrentSession().getTransaction().rollback();
            e.printStackTrace();
            chain.doFilter(req, res);
        }
    }
}
