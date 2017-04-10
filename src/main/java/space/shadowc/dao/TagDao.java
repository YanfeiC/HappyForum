package space.shadowc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.shadowc.domain.Tag;

/**
 * Created by cyf on 17-3-20.
 */
@Repository
public interface TagDao extends JpaRepository<Tag, Integer> {
}
