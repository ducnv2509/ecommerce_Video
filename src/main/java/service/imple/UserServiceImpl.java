package service.imple;

import DAO.UserDAO;
import DAO.dto.UserDto;
import DAO.impl.UserDaoImpl;
import constant.NameStored;
import entity.User;
import service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDaoImpl();
    }

    @Override
    public User findById(Integer id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User login(String username, String password) {
        return userDAO.findByUsernameAndPassword(username, password);
    }

    @Override
    public User resetPassword(String email) {
        User existUser = findByEmail(email);
        if (existUser != null) {
            String newPass = String.valueOf((int) (Math.random() * ((9999 - 1000) + 1)) + 1000);
            existUser.setPassword(newPass);
            return userDAO.update(existUser);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        return userDAO.findAll(pageNumber, pageSize);
    }

    @Override
    public User create(String username, String password, String email) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setAdmin(Boolean.FALSE);
        newUser.setActive(Boolean.TRUE);
        return userDAO.create(newUser);
    }

    @Override
    public User update(User entity) {
        return userDAO.update(entity);
    }

    @Override
    public User delete(String username) {
        User user = userDAO.findByUsername(username);
        user.setActive(Boolean.FALSE);
        return userDAO.update(user);
    }

    @Override
    public List<UserDto> findUserLikedVideoByHref(String href) {
        Map<String, Object> prams = new HashMap<>();
        prams.put(NameStored.NAME_parameters, href);
       List<User> users =  userDAO.findUsersLikedVideoByVideoHref(prams);
       List<UserDto> result = new ArrayList<>();
       users.forEach(user -> {
           UserDto dto = new UserDto();
           dto.setUsername(user.getUsername());
           dto.setEmail(user.getEmail());
           result.add(dto);
       });
       return result;
    }
}
