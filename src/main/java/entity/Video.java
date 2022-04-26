package entity;

import javax.persistence.*;

@Entity
@Table(name = "video")
public class Video {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "href")
    private String href;
    @Column(name = "poster")
    private String poster;
    @Column(name = "`views`")
    private Integer views;
    @Column(name = "share")
    private  Integer share;
    @Column(name = "`description`")
    private String description;
    @Column(name = "isActive")
    private  Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
