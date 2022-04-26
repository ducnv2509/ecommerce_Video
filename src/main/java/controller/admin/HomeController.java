package controller.admin;

import DAO.dto.UserDto;
import DAO.dto.VideoLikedInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import constant.SessionAttr;
import entity.User;
import service.StatsService;
import service.UserService;
import service.imple.StatsServiceImpl;
import service.imple.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/admin", "/admin/favorites"}, name = "HomeControllerAdmin")
public class HomeController extends HttpServlet {
    private StatsService statsService = new StatsServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
//        if (currentUser != null && currentUser.getAdmin()) {
            String path = request.getServletPath();
            switch (path) {
                case "/admin":
                    doGetHome(request, response);
                    break;
                case "/admin/favorites":
                    doGetFavorites(request, response);
                    break;
            }


//        } else {
//            response.sendRedirect("index");
//        }
    }

    protected void doGetHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VideoLikedInfo> videos = statsService.findVideoLikedInfo();
        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/views/admin/home.jsp").forward(request, response);
    }

    protected void doGetFavorites(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String videoHref = request.getParameter("href");
        List<UserDto> users = userService.findUserLikedVideoByHref(videoHref);
        if (users.isEmpty()) {
            response.setStatus(400);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String dataResponse = mapper.writeValueAsString(users);
            response.setStatus(200);
            out.print(dataResponse);
            out.flush();
            System.out.println("OKSSSS" + dataResponse);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
