package xyz.shi.servlet;
import xyz.shi.entity.User;
import xyz.shi.service.UserService;
import xyz.shi.service.impl.UserServiceImpl;
import xyz.shi.util.jdbcUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet("/userAdd")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int flag = userService.addUser(username, password);
        // 检查新增
        if (flag>0) {
            System.out.println("新增数据成功");
            List<User> users = userService.getAllUser();
            request.setAttribute("userList", users);
            request.getRequestDispatcher("homePage.jsp").forward(request, response);
        } else {
            // 登录失败，返回登录页面
            System.out.println("新增数据失败");
            request.setAttribute("message", "failed");

        }
    }
}
