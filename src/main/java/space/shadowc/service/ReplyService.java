package space.shadowc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.shadowc.dao.ReplyDao;
import space.shadowc.domain.Post;
import space.shadowc.domain.Reply;
import space.shadowc.domain.User;

/**
 * Created by cyf on 17-4-1.
 */
@Service
@Transactional
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;

    public Page<Reply> findByEditor(User user, int pageSize, Integer pageNumber) {
        Pageable pageable = new PageRequest(
                pageNumber - 1, pageSize, new Sort(
                new Sort.Order(Sort.Direction.DESC, "modifyTime")
        )
        );
        return replyDao.findByEditor(user, pageable);
    }

    public Page<Reply> findByPost(Post post,int pageSize,Integer pageNumber){
        Pageable pageable = new PageRequest(
                pageNumber -1 ,pageSize,new Sort(
                        new Sort.Order(Sort.Direction.DESC,"modifyTime")
        )
        );
        return replyDao.findByPost(post,pageable);
    }

    public void save(Reply reply){
        replyDao.save(reply);
    }
}
