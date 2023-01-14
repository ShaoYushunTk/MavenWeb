package web.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojo.User;
import service.UserService;

import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //获取复选框数据 判断用户是否选择记住我
        String remember = request.getParameter("remember");

        //2.调用service查询
        User user = service.login(username, password);

        //3.判断
        if (user != null){
            //登录成功

            //判断用户是否选择记住我
            if ("1".equals(remember)){
                //创建cookie
                Cookie cookieUsername = new Cookie("username",username);
                Cookie cookiePassword = new Cookie("password",password);

                //设置存活时间1周
                cookieUsername.setMaxAge(60*60*24*7);
                cookiePassword.setMaxAge(60*60*24*7);

                //发送
                response.addCookie(cookieUsername);
                response.addCookie(cookiePassword);

            }

            //把user存入session
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            //重定向
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/selectAllServlet");

        }else {
            //登录失败
            //存储错误信息
            request.setAttribute("login_msg","用户名或密码错误");

            //跳转到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
