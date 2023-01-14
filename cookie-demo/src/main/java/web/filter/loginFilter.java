package web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 登录验证过滤器
 */
@WebFilter("/*")
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //0.判断用户访问的路径是否为登录注册相关资源
        //是，放行，不是，进行登录验证
        String[] urls = {"/login.jsp","/css/","/imgs/","/loginServlet","/register.jsp","/register.html","/registerServlet","/checkCodeServlet","/selectUserServlet"};
        //获取当前访问资源路径
        String requestURL = req.getRequestURL().toString();
        //判断
        for (String u : urls){
            if (requestURL.contains(u)){
                //找到了放行
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }

        //1.判断session是否有user    判断用户是否登录

        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        //判断user是否为Null
        if (user != null){
            //用户已经登陆
            //放行
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            //用户没登录 跳转到登录页面
            req.setAttribute("login_msg","您尚未登录");
            req.getRequestDispatcher("/login.jsp").forward(servletRequest,servletResponse);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
