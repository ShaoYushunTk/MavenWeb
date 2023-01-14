package web.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应字符数据：设置字符数据响应体
 */
@WebServlet("/resp3")
public class ResponseDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("resp3...");

        //1.获取字符输出流
        PrintWriter writer = response.getWriter();
        //content-type 让浏览器识别html
        response.setHeader("content-type","text/html");
        writer.write("aaa");
        writer.write("你好");
        writer.write("<h1>aaa</h1>");

        //细节：流不需要关闭
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
