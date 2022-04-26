package DAO;

import entity.Video;

import java.util.List;

public interface VideoDAO {
    Video findById(Integer id);
    Video findByHref(String href);
    List<Video> findAll();
    List<Video> findAll(int pageNumber, int pageSize);
    Video create(Video entity);
    Video update(Video entity);
    Video delete(Video entity);
}
