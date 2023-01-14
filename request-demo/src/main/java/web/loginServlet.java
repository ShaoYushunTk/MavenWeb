package web;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.User;
import util.SqlSessionFactoryUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.调用mybatis完成查询
        //2.1获取sqlSessionFactory 代码官网入门复制
/*        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);*/

        //代码优化 保证工厂只创建一次
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.2获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.3获取mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //2.4调用方法
        User user = userMapper.select(username, password);
        //2.5释放资源
        sqlSession.close();


        //获取字符输出流 设置content type(中文输出需要设置)
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        //3.判断user是否为Null
        if (user != null){
            //登录成功
            writer.write("登录成功");
            //writer.write("login successfully");
        }else {
            //登录失败
            writer.write("登录失败");
            //writer.write("failed to login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
