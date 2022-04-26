package controller;

import constant.SessionAttr;
import entity.History;
import entity.User;
import entity.Video;
import service.HistoryService;
import service.VideoService;
import service.imple.HistoryServiceImpl;
import service.imple.VideoServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/video")
public class VideoController extends HttpServlet {
    private VideoService videoService = new VideoServiceImpl();
    private HistoryService historyService = new HistoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionParam = request.getParameter("action");
        String href = request.getParameter("id");
        HttpSession session = request.getSession();
        switch (actionParam) {
            case "watch":
                doGetWatch(session, href, request, response);
                break;
            case "like":
                doGetLike(session, href, request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGetWatch(HttpSession session, String href, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Video video = videoService.findByHref(href);
        request.setAttribute("video", video);
        User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
        if (user != null) {
            History history = historyService.create(user, video);
            request.setAttribute("flagLikeBtn", history.getLiked());
        }

        request.getRequestDispatcher("/views/users/video-detail.jsp").forward(request, response);
    }

    protected void doGetLike(HttpSession session, String href, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
        boolean result = historyService.updateLikeOrUnlike(user, href);
        if (result == true) {
            response.setStatus(204);
        } else {
            response.setStatus(400);
        }

    }
}
