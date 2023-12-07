package xyz.shi.servlet;

import xyz.shi.entity.User;
import xyz.shi.service.UserService;
import xyz.shi.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String session = (String) req.getSession().getAttribute("username");
        if (session != null) {
            UserService userService = new UserServiceImpl();
            List<User> users = userService.getAllUser();
            req.setAttribute("userList", users);
            req.getRequestDispatcher("homePage.jsp").forward(req, resp);;

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
         boolean login = userService.ableLogin(username, password);
        // 验证用户名和密码
        if (login) {
            // 登录成功，创建会话
            HttpSession session = request.getSession();
            List<User> users = userService.getAllUser();
            request.setAttribute("userList", users);

            session.setAttribute("username", username);
            System.out.println("登录成功");
            request.getRequestDispatcher("homePage.jsp").forward(request, response);;
        } else {
            // 登录失败，返回登录页面
            System.out.println("登录失败");
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        }
    }
}
