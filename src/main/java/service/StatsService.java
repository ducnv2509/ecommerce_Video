package service;

import DAO.dto.VideoLikedInfo;

import java.util.List;

public interface StatsService {
    List<VideoLikedInfo> findVideoLikedInfo();
}
