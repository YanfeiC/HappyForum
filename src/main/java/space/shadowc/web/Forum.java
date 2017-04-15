package space.shadowc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import space.shadowc.domain.Post;
import space.shadowc.domain.Reply;
import space.shadowc.domain.Topic;
import space.shadowc.domain.User;
import space.shadowc.service.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyf on 17-3-6.
 */
@Controller
public class Forum {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private AvatarGenerator avatarGenerator;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private InitData initData;

    @RequestMapping("/")
    public String showIndex(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        Page<Post> postpage = postService.findAll(10, 1);
        model.addAttribute("postpage", postpage);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "Index";
    }

    @GetMapping("/signin")
    public String showSignIn() {
        return "Signin";
    }

    @GetMapping("/t/{id}")
    public String showPost(@PathVariable("id") int id,
                           @RequestParam(value = "p", defaultValue = "1") int pagenum,
                           Model model) {
        Post post = postService.findById(id);
        Page<Reply> replyPage = replyService.findByPost(post, 100, pagenum);
        model.addAttribute("post", post);
        model.addAttribute("replyPage", replyPage);
        return "t";
    }

    @GetMapping("/initdata")
    public String initdata() {
        for (int k = 0; k < 2; k++) {
            List<Topic> topics = new ArrayList<>();
            List<User> users = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                topics.add(initData.initTopic());
                users.add(initData.initUser());
            }
            for (int i = 0; i < 10; i++) {
                Post post = initData.initPost(users.get(i), topics.get(i));
                for (User user : users) {
                    for (int j = 0; j < 2; j++) {
                        Reply reply = initData.initReply(user, post);
                    }
                }
            }
        }
        return "Index";
    }


}
