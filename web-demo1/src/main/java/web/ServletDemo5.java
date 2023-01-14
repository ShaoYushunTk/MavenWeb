package web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebServlet("/demo5")
public class ServletDemo5 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 根据请求方式不同 分别进行处理 自定义一个MyHttpServlet类实现下列代码，然后其他demo类继承这个MyHttpServlet类
        // 以上就是HttpServlet类的底层逻辑

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 1.获取请求方式
        String method = request.getMethod();
        // 2.判断
        if ("GET".equals(method)){
            // get方式处理逻辑
        }
        else if ("POST".equals(method)){
            // post方式处理逻辑
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
