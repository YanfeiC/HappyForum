package space.shadowc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.shadowc.dao.AuthorityDao;
import space.shadowc.domain.Authority;

/**
 * Created by cyf on 17-4-4.
 */
@Service
@Transactional
public class AuthorityService {
    @Autowired
    private AuthorityDao authorityDao;

    public void save(Authority authority) {
        authorityDao.save(authority);
    }
}
