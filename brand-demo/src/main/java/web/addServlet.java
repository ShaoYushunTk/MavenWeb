package web;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojo.Brand;
import service.BrandService;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addServlet")
public class addServlet extends HttpServlet {
    private BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收数据 request.getParameter()不能接受json数据
        //获取请求体
        BufferedReader br = request.getReader();
        String s = br.readLine();

        //将json字符串转为java对象
        Brand brand = JSON.parseObject(s, Brand.class);
        System.out.println(brand);

        //2.调用service添加
        brandService.add(brand);

        //3.响应成功标识
        response.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
