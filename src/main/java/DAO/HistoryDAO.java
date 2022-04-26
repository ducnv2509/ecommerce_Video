package DAO;

import entity.History;

import java.util.List;

public interface HistoryDAO {
    List<History> findByUser(String username);
    List<History> findByUserAndIsLiked(String username);
    History findByUserIdAndVideoId(Integer userId, Integer videoId);
    History create(History entity);
    History update(History entity);
}
