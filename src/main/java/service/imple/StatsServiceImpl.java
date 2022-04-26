package service.imple;

import DAO.StatsDAO;
import DAO.dto.VideoLikedInfo;
import DAO.impl.StatsDaoImpl;
import service.StatsService;

import java.util.List;

public class StatsServiceImpl implements StatsService {
    private StatsDAO statsDAO;

    public StatsServiceImpl() {
        statsDAO = new StatsDaoImpl();
    }

    @Override
    public List<VideoLikedInfo> findVideoLikedInfo() {
        return statsDAO.findVideoLikedInfo();
    }
}
