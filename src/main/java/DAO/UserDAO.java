package DAO;

import entity.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    User findById(Integer id);

    User findByEmail(String email);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findAll();

    List<User> findAll(int pageNumber, int pageSize);

    User create(User entity);

    User update(User entity);

    User delete(User entity);

    List<User> findUsersLikedVideoByVideoHref(Map<String, Object> params);
}
