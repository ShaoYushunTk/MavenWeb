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

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //封装用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //2.调用mapper 根据用户名查询用户对象
        //2.1获取sqlSessionFactory 代码官网入门复制
        /*String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);*/

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.2获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.3获取mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //2.4调用方法
        User u = userMapper.selectByUsername(username);

        //判断用户对象是否为null 保证username唯一
        if (u == null){
            // 添加用户
            userMapper.add(user);

            //提交事务
            sqlSession.commit();

            //释放资源
            sqlSession.close();

            //
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("注册成功");
        } else {
            //提示修改用户名
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户名已存在");

        }


        //释放资源
        sqlSession.close();


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
