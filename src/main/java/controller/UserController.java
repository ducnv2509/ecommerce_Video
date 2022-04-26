package controller;

import constant.SessionAttr;
import entity.User;
import service.EmailService;
import service.UserService;
import service.imple.EmailServiceImpl;
import service.imple.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout", "/register", "/forgotPass", "/changePass"})
public class UserController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();
    private final EmailService emailService = new EmailServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession sessions = request.getSession();
        switch (path) {
            case "/login":
                doGetLogin(request, response);
                break;
            case "/logout":
                doGetLogout(sessions, request, response);
                break;
            case "/register":
                doGetRegister(request, response);
                break;
            case "/forgotPass":
                doGetForgotPass(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession sessions = request.getSession();
        switch (path) {
            case "/login":
                doPostLogin(sessions, request, response);
                break;
            case "/register":
                doPostRegister(sessions, request, response);
                break;
            case "/forgotPass":
                doPostForgotPass(request, response);
                break;
            case "/changePass":
                doPostChangePass(sessions, request, response);
                break;
        }
    }

    protected void doGetLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/users/login.jsp").forward(request, response);

    }

    protected void doGetLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session.removeAttribute(SessionAttr.CURRENT_USER);
        response.sendRedirect("index");
    }

    protected void doGetRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/users/register.jsp").forward(request, response);

    }

    protected void doGetForgotPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/users/forgot-password.jsp").forward(request, response);

    }

    protected void doPostForgotPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String email = request.getParameter("email");
        User userWithNewPass = userService.resetPassword(email);
        if (userWithNewPass != null) {
            emailService.SendEmail(getServletContext(), userWithNewPass, SessionAttr.TYPE_EMAIL_FORGOT);
            response.setStatus(204);
        } else {
            response.setStatus(400);
        }
    }


    protected void doPostChangePass(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String currentPass = request.getParameter("currentPass");
        String newPass = request.getParameter("newPass");
        User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
        if (user.getPassword().equals(currentPass)) {
            user.setPassword(newPass);
            User updateUser = userService.update(user);
            if (updateUser != null) {
                session.setAttribute(SessionAttr.CURRENT_USER, updateUser);
                response.setStatus(204);
            }else{
                response.setStatus(400);
            }
        } else {
            response.setStatus(400);
        }
    }

    protected void doPostLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute(SessionAttr.CURRENT_USER, user);
            response.sendRedirect("index");
        } else {
            response.sendRedirect("login");
        }
    }

    protected void doPostRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = userService.create(username, password, email);
        if (user != null) {
            emailService.SendEmail(getServletContext(), user, SessionAttr.TYPE_EMAIL_WELCOME);
            session.setAttribute(SessionAttr.CURRENT_USER, user);
            response.sendRedirect("index");
        } else {
            response.sendRedirect("register");
        }
    }


}
