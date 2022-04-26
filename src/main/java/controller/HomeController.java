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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/index", "/favorites", "/history"})
public class HomeController extends HttpServlet {

    private final VideoService videoService = new VideoServiceImpl();
    private final HistoryService historyService = new HistoryServiceImpl();
    private static final int VIDEO_MAX_PAGE_SIZE = 2;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession sessions = request.getSession();
        switch (path) {
            case "/index":
                doGetIndex(request, response);
                break;
            case "/favorites":
                doGetFavorites(sessions, request, response);
                break;
            case "/history":
                doGetHistory(sessions, request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Video> countVideo = videoService.findAll();
        int maxPage = (int) Math.ceil(countVideo.size() / (double) VIDEO_MAX_PAGE_SIZE);
        request.setAttribute("maxPage", maxPage);
        List<Video> videoList;
        String pageNumber = request.getParameter("page");
        if (pageNumber == null || Integer.parseInt(pageNumber) > maxPage) {
            videoList = videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
            request.setAttribute("currentPage", 1);
        } else {
            videoList = videoService.findAll(Integer.parseInt(pageNumber), VIDEO_MAX_PAGE_SIZE);
            request.setAttribute("currentPage", Integer.parseInt(pageNumber));
        }
        request.setAttribute("videos", videoList);
        request.getRequestDispatcher("/views/users/index.jsp").forward(request, response);
    }

    protected void doGetFavorites(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
        List<History> histories = historyService.findByUserAndIsLiked(user.getUsername());
        List<Video> videoList = new ArrayList<>();
        histories.forEach(item -> videoList.add(item.getVideo()));
        request.setAttribute("videos", videoList);
        request.getRequestDispatcher("/views/users/favorites.jsp").forward(request, response);
    }


    protected void doGetHistory(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
        List<History> histories = historyService.findByUser(user.getUsername());
        List<Video> videoList = new ArrayList<>();
        histories.forEach(item -> videoList.add(item.getVideo()));
        request.setAttribute("videos", videoList);
        request.getRequestDispatcher("/views/users/history.jsp").forward(request, response);
    }
}
