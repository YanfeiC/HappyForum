package space.shadowc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.shadowc.domain.Post;
import space.shadowc.domain.Reply;
import space.shadowc.domain.User;

/**
 * Created by cyf on 17-3-20.
 */
@Repository
public interface ReplyDao extends JpaRepository<Reply, Integer> {
    Page<Reply> findByEditor(User user, Pageable pageable);
    Page<Reply> findByPost(Post post,Pageable pageable);
}
