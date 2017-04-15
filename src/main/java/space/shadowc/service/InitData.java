package space.shadowc.service;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.text.TextProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import space.shadowc.domain.*;

import java.util.Date;
import java.util.Locale;

/**
 * Created by cyf on 17-4-14.
 */
@Service
public class InitData {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private AvatarGenerator avatarGenerator;

    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    public Topic initTopic(){
        Fairy fairy = Fairy.create(Locale.forLanguageTag("en"));
        Person person = fairy.person();
        TextProducer text = fairy.textProducer();

        Topic topic = new Topic();
        topic.setName(text.word(5));
        topicService.save(topic);
        return topic;
    }

    public User initUser(){
        Fairy fairy = Fairy.create(Locale.forLanguageTag("en"));
        Person person = fairy.person();
        TextProducer text = fairy.textProducer();

        User user = new User();
        user.setUsername(person.getUsername());
        user.setPassword(new StandardPasswordEncoder("happy").encode("12345"));
        user.setEnabled(true);
        user.setEmail(person.getEmail());
        user.setJoinTime(new Date());
        user.setAvatar(avatarGenerator.getAvatar());
        user.setCredit(0);
        userService.save(user);

        Authority authority = new Authority();
        authority.setUsername(person.getUsername());
        authority.setAuthority("ROLE_USER");
        authorityService.save(authority);
        return user;
    }

    public Post initPost(User user,Topic topic){
        Fairy fairy = Fairy.create(Locale.forLanguageTag("en"));
        Person person = fairy.person();
        TextProducer text = fairy.textProducer();

        Post post = new Post();
        post.setEditor(user);
        post.setTitle(text.sentence(20));
        post.setContent(text.paragraph(200));
        post.setCreateTime(new Date());
        post.setLastReplyTime(new Date());
        post.setVoteCount(20);
        post.setPageView(450);
        post.setTopic(topic);
        postService.save(post);
        return post;
    }

    public Reply initReply(User user,Post post){
        Fairy fairy = Fairy.create(Locale.forLanguageTag("en"));
        Person person = fairy.person();
        TextProducer text = fairy.textProducer();

        Reply reply = new Reply();
        reply.setEditor(user);
        reply.setContent(text.sentence(40));
        reply.setModifyTime(new Date());
        reply.setPost(post);
        replyService.save(reply);
        return reply;
    }
}
