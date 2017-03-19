package space.shadowc.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cyf on 17-3-17.
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "users")
public class User extends BaseDomain {
    /**
     * 用户锁定的状态值
     */
    public static final int ABLE = 1;

    /**
     * 用户正常的状态值
     */
    public static final int UNABLE = 0;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username")
    private String userName;

    private int enabled = ABLE;

    private String email;

    @Column(name = "join_time")
    private Date joinTime;

    private String avatar;

    @Column(name = "description")
    private String desc;

    @Column(name = "last_visit")
    private Date lastVisit;

    private String credit;

    private String passWord;

    @OneToMany(mappedBy = "editor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "editor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}

