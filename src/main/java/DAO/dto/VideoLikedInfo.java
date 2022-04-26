package DAO.dto;

public class VideoLikedInfo {
    private Integer VideoId;
    private String title;
    private String href;
    private Integer totalLike;


    public Integer getVideoId() {
        return VideoId;
    }

    public void setVideoId(Integer videoId) {
        VideoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(Integer totalLike) {
        this.totalLike = totalLike;
    }
}
