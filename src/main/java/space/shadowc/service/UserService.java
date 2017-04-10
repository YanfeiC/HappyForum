package space.shadowc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.shadowc.dao.UserDao;
import space.shadowc.domain.User;

/**
 * Created by cyf on 17-3-26.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public boolean usernameExist(String username) {
        return (findByUsername(username) == null) ? false : true;
    }

    public boolean emailExist(String email) {
        return (findByEmail(email) == null) ? false : true;
    }

    public void save(User user) {
        userDao.save(user);
    }
}
