package space.shadowc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.shadowc.domain.Authority;

/**
 * Created by cyf on 17-4-4.
 */
@Repository
public interface AuthorityDao extends JpaRepository<Authority,Integer>{

}
