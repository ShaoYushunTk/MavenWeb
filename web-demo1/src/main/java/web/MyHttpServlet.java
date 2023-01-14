package web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class MyHttpServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 根据请求方式不同 分别进行处理

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 1.获取请求方式
        String method = request.getMethod();
        // 2.判断
        if ("GET".equals(method)){
            // get方式处理逻辑
            doGet(servletRequest,servletResponse);
        }
        else if ("POST".equals(method)){
            // post方式处理逻辑
            doPost(servletRequest,servletResponse);
        }

    }



    /**
     * 其他类重写get方法和post方法
     * @param servletRequest
     * @param servletResponse
     */
    protected void doGet(ServletRequest servletRequest, ServletResponse servletResponse) {

    }
    protected void doPost(ServletRequest servletRequest, ServletResponse servletResponse) {

    }
    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }


}
