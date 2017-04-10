package space.shadowc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.shadowc.domain.User;

/**
 * Created by cyf on 17-3-20.
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}
