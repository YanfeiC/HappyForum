package space.shadowc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.shadowc.domain.Topic;

import java.util.List;

/**
 * Created by cyf on 17-3-20.
 */
@Repository
public interface TopicDao extends JpaRepository<Topic, Integer> {
     List<Topic> findAll();
     Topic findByName(String name);
}
