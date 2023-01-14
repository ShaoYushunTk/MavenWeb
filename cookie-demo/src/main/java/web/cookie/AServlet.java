package web.cookie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/aServlet")
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //发送cookie

        //1.创建cookie对象
        Cookie cookie = new Cookie("username", "zs");


        /**
         * 设置cookie存活时间 1week 默认浏览器关闭放在内存的cookie销毁
         * 正数：cookie写入硬盘，到期自动删除
         * 负数：默认
         * 0 删除对应cookie
         */
        cookie.setMaxAge(60*60*24*7);

        //2.发送cookie
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
