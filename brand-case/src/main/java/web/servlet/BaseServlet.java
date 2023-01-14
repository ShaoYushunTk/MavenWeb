package web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 替换httpServlet
 * 根据请求的最后一段路径进行方法分发
 */
public class BaseServlet extends HttpServlet {

    //根据请求的最后一段路径进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求路径  /brand/selectAll  /brand/add
        String requestURI = req.getRequestURI(); // /brand_case_war/brand/selectAll
        //获取最后一段路径（方法名）
        int index = requestURI.lastIndexOf('/'); // /selectAll
        String methodName = requestURI.substring(index + 1);

        //2.执行方法
        //2.1获取BrandServlet/UserServlet 字节码对象
        // 谁调用我(this 所在的方法)，我(this)代表谁
        // BrandServlet继承BaseServlet，会调用BaseServlet的service方法，因此this代表BrandServlet
        Class<? extends BaseServlet> cls = this.getClass();

        //2.2获取方法method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //2.3执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
