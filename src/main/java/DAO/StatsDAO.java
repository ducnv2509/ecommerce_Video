package DAO;

import DAO.dto.VideoLikedInfo;
import entity.User;

import java.util.List;

public interface StatsDAO {
    List<VideoLikedInfo> findVideoLikedInfo();
}
