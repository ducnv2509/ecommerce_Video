package service.imple;

import DAO.HistoryDAO;
import DAO.impl.HistoryDaoImpl;
import entity.History;
import entity.User;
import entity.Video;
import service.HistoryService;
import service.VideoService;

import java.sql.Timestamp;
import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    private HistoryDAO dao;
    private VideoService videoService = new VideoServiceImpl();

    public HistoryServiceImpl() {
        dao = new HistoryDaoImpl();
    }

    @Override
    public List<History> findByUser(String username) {
        return dao.findByUser(username);
    }

    @Override
    public List<History> findByUserAndIsLiked(String username) {
        return dao.findByUserAndIsLiked(username);
    }

    @Override
    public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
        return dao.findByUserIdAndVideoId(userId, videoId);
    }

    @Override
    public History create(User user, Video video) {
        History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
        if (existHistory == null) {
            existHistory = new History();
            existHistory.setUser(user);
            existHistory.setVideo(video);
            existHistory.setLiked(Boolean.FALSE);
            existHistory.setViewDate(new Timestamp(System.currentTimeMillis()));
            dao.create(existHistory);
        }
        return existHistory;
    }

    @Override
    public boolean updateLikeOrUnlike(User user, String href) {
        Video video = videoService.findByHref(href);
        History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
        if (!existHistory.getLiked()) {
            existHistory.setLiked(true);
            existHistory.setLikeDate(new Timestamp(System.currentTimeMillis()));
        } else {
            existHistory.setLiked(false);
            existHistory.setLikeDate(null);
        }
        History updateHistory = dao.update(existHistory);
        return updateHistory != null ? true : false;
    }
}
