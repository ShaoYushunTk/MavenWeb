package web.cookie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/bServlet")
public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cookie
        //1.获取cookie数组
        Cookie[] cookies = request.getCookies();

        //2.遍历
        for (Cookie cookie : cookies){
            //3.获取数据
            String name = cookie.getName();
            if ("username".equals(name)){
                String value = cookie.getValue();

                System.out.println(name + " : " + value);
                break;
            }

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
