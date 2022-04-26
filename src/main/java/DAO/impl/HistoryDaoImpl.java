package DAO.impl;

import DAO.AbstractDAO;
import DAO.HistoryDAO;
import entity.History;

import java.util.List;

public class HistoryDaoImpl extends AbstractDAO<History> implements HistoryDAO {
    @Override
    public List<History> findByUser(String username) {
        String sql = "select o from History o where o.user.username = ?0  and o.video.isActive = 1" +
                " order by o.viewDate desc";
        return super.findMany(History.class, sql, username);
    }

    @Override
    public List<History> findByUserAndIsLiked(String username) {
        String sql = "select o from History o where o.user.username = ?0  and o.video.isActive = 1 and o.isLiked = 1" +
                " order by o.viewDate desc";
        return super.findMany(History.class, sql, username);
    }

    @Override
    public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
        String sql = "select o from History o where o.user.id = ?0 and o.video.id = ?1" +
                " and o.video.isActive = 1";
        return super.findOne(History.class, sql, userId, videoId);
    }
}
