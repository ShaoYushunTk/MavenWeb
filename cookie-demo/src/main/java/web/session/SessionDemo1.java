package web.session;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/demo1")
public class SessionDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //存储到session 一次会话多次请求获取的session对象是一样的
        /**
         * session
         * 钝化：服务器正常关闭后，tomcat自动把session数据写入硬盘的文件
         * 活化：再次启动服务器，从文件中加载数据到session
         * 销毁：1.默认情况下无操作，30分钟自动销毁   web.xml设置
         *      2.调用session对象的invalidate()  session.invalidate()   可以实现退出登录
         *
         */
        //1.获取session对象
        HttpSession session = request.getSession();
        //2.设置数据
        session.setAttribute("username","zs");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
