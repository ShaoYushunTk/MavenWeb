package web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Ajax 异步javascript和xml
 * 可用于验证用户名是否存在，当用户名输入框失去焦点时，校验用户名是否存在，而不需要输入所有数据后提交时再校验
 */
@WebServlet("/axiosServlet")
public class axiosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get...");

        //接收请求参数
        String username = request.getParameter("username");
        System.out.println(username);

        //2.响应数据
        response.getWriter().write("hello Axios");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post...");
        this.doGet(request, response);
    }
}
