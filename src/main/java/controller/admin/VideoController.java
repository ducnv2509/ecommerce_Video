package controller.admin;

import constant.SessionAttr;
import entity.User;
import entity.Video;
import org.apache.commons.beanutils.BeanUtils;
import service.VideoService;
import service.imple.VideoServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet(name = "VideoController", urlPatterns = {"/admin/video"})
public class VideoController extends HttpServlet {
    private VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
//        if (currentUser != null && currentUser.getAdmin()) {
        String action = request.getParameter("action");
        switch (action) {
            case "view":
                doGetOverView(request, response);
                break;
            case "delete":
                doGetDelete(request, response);
                break;
            case "add":
                request.setAttribute("isEdit", false);
                doGetAdd(request, response);
                break;
            case "edit":
                request.setAttribute("isEdit", true);
                doGetEdit(request, response);
                break;
        }
//        } else {
//            response.sendRedirect("index");
//        }
    }

    protected void doGetOverView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Video> videos = videoService.findAll();
        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/views/admin/video-overview.jsp").forward(request, response);
    }

    protected void doGetAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/video-edit.jsp").forward(request, response);
    }

    protected void doGetEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String href = request.getParameter("href");
        Video video = videoService.findByHref(href);
        request.setAttribute("video", video);
        request.getRequestDispatcher("/views/admin/video-edit.jsp").forward(request, response);
    }

    protected void doGetDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String href = request.getParameter("href");
        Video videoDelete = videoService.delete(href);
        if (videoDelete != null) {
            response.setStatus(204);
        } else {
            response.setStatus(400);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
//        if (currentUser != null && currentUser.getAdmin()) {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                doPostAdd(request, response);
                break;
            case "edit":
                doPostEdit(request, response);
                break;

        }
//        } else {
//            response.sendRedirect("index");
//        }
    }

    protected void doPostAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Video video = new Video();
        try {
            BeanUtils.populate(video, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Video videoInsert = videoService.create(video);
        if (videoInsert != null) {
            response.setStatus(204);
        } else {
            response.setStatus(400);
        }
    }

    protected void doPostEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        Video video = videoService.findByHref(request.getParameter("href"));
        try {
            BeanUtils.populate(video, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Video videoInsert = videoService.update(video);
        if (videoInsert != null) {
            response.setStatus(204);
        } else {
            response.setStatus(400);
        }
    }
}
