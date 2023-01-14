package web.servlet;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Brand;
import pojo.PageBean;
import service.BrandService;
import service.impl.BrandServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * 一个servlet写一个方法，较为繁琐
 * 使用BrandServlet将所有功能如add,select,update集中到一个servlet
 * 根据路径分发方法
 */
@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service
        List<Brand> brands = brandService.selectAll();

        //2.转为json
        String jsonString = JSON.toJSONString(brands);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收数据
        BufferedReader bufferedReader = request.getReader();
        String s = bufferedReader.readLine(); //json字符串

        //转为brand对象
        Brand brand = JSON.parseObject(s, Brand.class);

        //2.调用service
        brandService.add(brand);

        //3.响应成功标识
        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收数据
        BufferedReader bufferedReader = request.getReader();
        String s = bufferedReader.readLine(); //json字符串

        //转为brand对象
        Brand brand = JSON.parseObject(s, Brand.class);

        //2.调用service
        brandService.update(brand);

        //3.响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 删除单个数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收数据
        BufferedReader bufferedReader = request.getReader();
        String id = bufferedReader.readLine();

        //2.调用service
        brandService.delete(Integer.parseInt(id));

        //3.响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收数据
        BufferedReader bufferedReader = request.getReader();
        String s = bufferedReader.readLine(); //json字符串

        //转为int[]
        int[] ids = JSON.parseObject(s, int[].class);

        //2.调用service
        brandService.deleteByIds(ids);

        //3.响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收当前页码和每页展示条数   url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //1.调用service
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //2.转为json
        String jsonString = JSON.toJSONString(pageBean);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收当前页码和每页展示条数   url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象 接收请求体内参数 post
        BufferedReader bufferedReader = request.getReader();
        String s = bufferedReader.readLine(); //json字符串

        //转为Brand
        Brand brand = JSON.parseObject(s, Brand.class);

        //1.调用service
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        //2.转为json
        String jsonString = JSON.toJSONString(pageBean);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }



}
