package space.shadowc.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created by cyf on 17-4-4.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "authorities")
public class Authority extends BaseDomain {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    private String authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }


    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
