package web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * 过滤器，JavaWeb三大组件之一
 * @WebFilter 设置拦截路径
 *
 * filter1放行前逻辑->filter2放行前逻辑->放行->访问资源->filter2放行后逻辑->filter1放行后逻辑
 *
 */
//@WebFilter("/hello.jsp")
@WebFilter("/*")
public class FilterDemo2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * 执行放行前逻辑->放行->访问资源->执行放行后逻辑
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //放行前逻辑
        //一般放行前处理request
        System.out.println("2.filter...");

        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        //放行后逻辑
        //一般放行后处理response
        System.out.println("4.filter...");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
