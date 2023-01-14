package web.response;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/resp1")
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("resp1...");

        //重定向
/*        //1.设置响应状态码 302
        response.setStatus(302);
        //2.设置响应头
        response.setHeader("Location","/request_demo_war/resp2"); // resp2*/

        //简化方式
        //response.sendRedirect("resp2");
        response.sendRedirect("https://google.com.hk");

        //转发forward和重定向redirect区别
        /**
         * 重定向：1.浏览器地址栏路径发生变化
         *       2.可以重定向到任意位置资源（服务器内部外部均可）
         *       3.两次请求，不能在多个资源使用request共享数据
         * 转发：1.浏览器地址栏路径不发生变化
         *      2.只能转发到当前服务器内部资源
         *      3.一次请求，可以在转发的资源之间使用request共享数据
         */

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
