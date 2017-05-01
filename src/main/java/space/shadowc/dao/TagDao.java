package space.shadowc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.shadowc.domain.Tag;

import java.util.List;

/**
 * Created by cyf on 17-3-20.
 */
@Repository
public interface TagDao extends JpaRepository<Tag, Integer> {
    List<Tag> findAll();
    Tag findByName(String name);
}
