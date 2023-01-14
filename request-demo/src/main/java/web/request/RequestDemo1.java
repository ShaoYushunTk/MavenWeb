package web.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/req1")
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (String key : parameterMap.keySet()){
            System.out.print(key + ":");

            for (String value : parameterMap.get(key)){
                System.out.print(value + " ");
            }
            System.out.println();
        }

        System.out.println("--------");

        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies){
            System.out.println(hobby);
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("username: " + username);
        System.out.println("password: " + password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
