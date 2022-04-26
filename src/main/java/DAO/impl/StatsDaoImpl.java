package DAO.impl;

import DAO.AbstractDAO;
import DAO.StatsDAO;
import DAO.dto.VideoLikedInfo;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class StatsDaoImpl extends AbstractDAO<Object[]> implements StatsDAO {
    @Override
    public List<VideoLikedInfo> findVideoLikedInfo() {
        String sql = "select  video.id, title, href,\n" +
                "       sum(cast(isLiked as int)) as totalLike\n" +
                "       from video join history h on video.id = h.videoId\n" +
                "where isActive = 1 group by video.id, title, href\n" +
                "order by  sum(cast(isLiked as int)) desc";
        List<Object[]> objects = super.findManyByNativeQuery(sql);
        List<VideoLikedInfo> result = new ArrayList<>();
        objects.forEach(obj -> {
            VideoLikedInfo videoLikedInfo = setDataVideoLikedInfo(obj);
            result.add(videoLikedInfo);
        });
        return result;
    }


    public VideoLikedInfo setDataVideoLikedInfo(Object[] obj) {
        VideoLikedInfo videoLikedInfo = new VideoLikedInfo();
        videoLikedInfo.setVideoId((Integer) obj[0]);
        videoLikedInfo.setTitle((String) obj[1]);
        videoLikedInfo.setHref((String) obj[2]);
        videoLikedInfo.setTotalLike(obj[3] == null ? 0 : (Integer) obj[3]);
        return videoLikedInfo;
    }
}
