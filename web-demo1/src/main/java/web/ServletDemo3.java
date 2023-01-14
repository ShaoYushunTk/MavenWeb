package web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

/**
 * ctrl + i 生成override函数
 * 创建servletDemo需要实现servlet接口
 */
@WebServlet(urlPatterns = "/demo3", loadOnStartup = 1)
public class ServletDemo3 implements Servlet {
    private ServletConfig config;
    /**
     * 初始化方法
     * 调用时机：默认情况下，servlet被第一次访问时调用
     *      loadOnStartUp 设置为正整数，则在服务器创建时调用，数字越小优先级越高
     *                    默认情况下为-1，即在servlet被第一次访问时调用
     * 调用次数：1
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
        System.out.println("init...");
    }

    /**
     * 获取ServletConfig对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {

        return config;
    }

    /**
     * 提供服务的方法
     * 调用时机：每一次servlet被访问时 都会调用
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet hello world");
    }

    /**
     * 返回servlet相关信息
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁方法
     * 调用时机：内存释放或服务器关闭
     * 调用次数：一次
     */
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}
