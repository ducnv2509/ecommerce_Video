package entity;

import constant.NameStored;

import javax.persistence.*;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = NameStored.STORED_FIND_USERS_LIKE_VIDEO_HREF,
                procedureName = "sp_SelectUsersLikeVideo",
                resultClasses = {User.class},
                parameters = @StoredProcedureParameter(name = NameStored.NAME_parameters, type = String.class)
        )
})
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "isAdmin")
    private Boolean isAdmin;
    @Column(name = "isActive")
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
