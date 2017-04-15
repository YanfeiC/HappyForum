package space.shadowc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.shadowc.domain.Post;
import space.shadowc.domain.User;

/**
 * Created by cyf on 17-3-20.
 */
@Repository
public interface PostDao extends JpaRepository<Post, Integer> {

   Page<Post> findByEditor(User editor, Pageable pageable);

   Post findById(int id);

   Page<Post> findAll(Pageable pageable);

}
