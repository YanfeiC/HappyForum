package space.shadowc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.shadowc.dao.TopicDao;
import space.shadowc.domain.Topic;

import java.util.List;

/**
 * Created by cyf on 17-4-14.
 */
@Service
@Transactional
public class TopicService {
    @Autowired
    private TopicDao topicDao;

    public void save(Topic topic) {
        topicDao.save(topic);
    }

    public List<Topic> findAll() {
        return topicDao.findAll();
    }

    public Topic findByName(String name) {
        return topicDao.findByName(name);
    }
}
