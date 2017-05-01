package space.shadowc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.shadowc.dao.TagDao;
import space.shadowc.domain.Tag;

import java.util.List;

/**
 * Created by cyf on 17-5-1.
 */
@Service
public class TagService {
    @Autowired
    private TagDao tagDao;

    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    public Tag findByName(String name) {
        return tagDao.findByName(name);
    }

    public Boolean tagValidator(String[] tags) {
        for (String tag : tags) {
            if (findByName(tag) == null) {
                return false;
            }
        }
        return true;
    }
}
