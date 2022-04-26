package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "history")
public class History {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "videoId", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Video video;
    @Column(name = "viewDate")
    @CreationTimestamp
    private Timestamp viewDate;
    @Column(name = "isLiked")
    private Boolean isLiked;
    @Column(name = "likeDate")
    private Timestamp likeDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Timestamp getViewDate() {
        return viewDate;
    }

    public void setViewDate(Timestamp viewDate) {
        this.viewDate = viewDate;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public Timestamp getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Timestamp likeDate) {
        this.likeDate = likeDate;
    }
}
