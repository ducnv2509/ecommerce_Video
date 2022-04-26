package service.imple;

import DAO.VideoDAO;
import DAO.impl.VideoDaoImpl;
import entity.Video;
import service.VideoService;

import java.util.List;

public class VideoServiceImpl implements VideoService {
    private final VideoDAO videoDAO;

    public VideoServiceImpl() {
        videoDAO = new VideoDaoImpl();
    }

    @Override
    public Video findById(Integer id) {
        return videoDAO.findById(id);
    }

    @Override
    public Video findByHref(String href) {
        return videoDAO.findByHref(href);
    }

    @Override
    public List<Video> findAll() {
        return videoDAO.findAll();
    }

    @Override
    public List<Video> findAll(int pageNumber, int pageSize) {
        return videoDAO.findAll(pageNumber, pageSize);
    }

    @Override
    public Video create(Video entity) {
        entity.setActive(Boolean.TRUE);
        entity.setViews(0);
        entity.setShare(0);
        return videoDAO.create(entity);
    }

    @Override
    public Video update(Video entity) {
        return videoDAO.update(entity);
    }

    @Override
    public Video delete(String href) {
        Video entity = findByHref(href);
        entity.setActive(Boolean.FALSE);
        return videoDAO.update(entity);
    }
}
