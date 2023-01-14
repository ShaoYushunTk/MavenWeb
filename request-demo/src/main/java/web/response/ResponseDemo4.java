package web.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应字节数据：设置字节数据响应体
 */
@WebServlet("/resp4")
public class ResponseDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("resp4...");

        //1.读取文件
        FileInputStream fileInputStream = new FileInputStream("d://Desktop/11.jpg");

        //2.获取字节输出流
        ServletOutputStream outputStream = response.getOutputStream();

        //完成流的copy
        /*byte[] buff = new byte[1024];
        int len = 0;
        while((len = fileInputStream.read(buff)) != -1){
            outputStream.write(buff,0,len);
        }*/

        //pom导入common-io
        IOUtils.copy(fileInputStream,outputStream);

        fileInputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
