package web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.User;
import service.UserService;

import java.io.IOException;

@WebServlet("/selectUserServlet")
public class selectUserServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户名
        String username = request.getParameter("username");

        //2.调用service查询user对象
        boolean flag = service.register(username);

        if (flag) {
            request.setAttribute("register_msg","用户名存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }

        //3.响应标记
        response.getWriter().write("" + flag);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
