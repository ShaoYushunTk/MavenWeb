package service;

import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.User;
import util.SqlSessionFactoryUtils;

public class UserService {
    SqlSessionFactory factory =  SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = mapper.select(username, password);
        //释放资源
        sqlSession.close();

        return user;
    }

    /**
     * 注册方法
     * @param user
     * @return
     */
    public boolean register(User user) {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //判断用户名是否存在
        User u = mapper.selectByUsername(user.getUsername());

        if (u == null) {
            //用户名不存在，可以注册
            mapper.add(user);
            sqlSession.commit();
        }
        //释放资源
        sqlSession.close();

        return u == null;

    }


    /**
     * 判断用户名是否存在 验证用户名是否可用
     * @param username
     * @return
     */
    public boolean register(String username) {
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //判断用户名是否存在
        User u = mapper.selectByUsername(username);

        //释放资源
        sqlSession.close();

        return u != null;

    }
}
