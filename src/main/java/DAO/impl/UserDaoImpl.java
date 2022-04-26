package DAO.impl;

import DAO.AbstractDAO;
import DAO.UserDAO;
import constant.NameStored;
import entity.User;

import java.util.List;
import java.util.Map;

public class UserDaoImpl extends AbstractDAO<User> implements UserDAO {
    @Override
    public User findById(Integer id) {
        return super.findById(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        String sql = "select o from User o where o.email = ?0";
        return super.findOne(User.class, sql, email);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select o from User o where o.username = ?0";
        return super.findOne(User.class, sql, username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "select o from User o where o.username = ?0 and o.password = ?1";
        return super.findOne(User.class, sql, username, password);
    }

    @Override
    public List<User> findAll() {
        return super.findAll(User.class, true);
    }

    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        return super.findAll(User.class, true, pageNumber, pageSize);
    }

    @Override
    public List<User> findUsersLikedVideoByVideoHref(Map<String, Object> params) {
        return super.callStored(NameStored.STORED_FIND_USERS_LIKE_VIDEO_HREF, params);
    }
}
