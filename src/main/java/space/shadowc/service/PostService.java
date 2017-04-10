package space.shadowc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.shadowc.dao.PostDao;
import space.shadowc.domain.Post;
import space.shadowc.domain.User;

/**
 * Created by cyf on 17-4-1.
 */
@Service
@Transactional
public class PostService {
    @Autowired
    private PostDao postDao;

    public Page<Post> findByEditor(User user, int pageSize, Integer pageNumber) {
        Pageable pageable = new PageRequest(
                pageNumber - 1, pageSize, new Sort(
                new Sort.Order(Sort.Direction.ASC, "lastReplyTime")
        )
        );
        return postDao.findByEditor(user, pageable);
    }

    public Page<Post> findAll(int pageSize,Integer pageNumber){
        Pageable pageable = new PageRequest(
                pageNumber-1,pageSize,new Sort(
                        new Sort.Order(Sort.Direction.ASC,"lastReplyTime")
        )
        );
        return postDao.findAll(pageable);
    }
}
