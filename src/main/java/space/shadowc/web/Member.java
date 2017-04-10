package space.shadowc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import space.shadowc.domain.Post;
import space.shadowc.domain.Reply;
import space.shadowc.domain.User;
import space.shadowc.service.PostService;
import space.shadowc.service.ReplyService;
import space.shadowc.service.UserService;

/**
 * Created by cyf on 17-4-6.
 */
@Controller
@RequestMapping("/member")
public class Member {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    @GetMapping("/{username}")
    public String getMember(@PathVariable("username") String username, Model model) {

        User user = userService.findByUsername(username);
        Page<Post> postPage = postService.findByEditor(user, 10, 1);
        Page<Reply> replyPage = replyService.findByEditor(user, 10, 1);

        model.addAttribute("user", user);
        model.addAttribute("postpage", postPage);
        model.addAttribute("replypage", replyPage);

        return "Member";
    }

    @GetMapping("/{username}/posts")
    public String getposts(
            @PathVariable("username") String username,
            @RequestParam(value = "p", defaultValue = "1") int pageNum,
            Model model) {
        User user = userService.findByUsername(username);
        Page<Post> postPage = postService.findByEditor(user, 20, pageNum);
        model.addAttribute("user",user);
        model.addAttribute("postpage", postPage);
        model.addAttribute("currentpage",pageNum);
        return "Posts";
    }

    @GetMapping("/{username}/replies")
    public String getReplies(
            @PathVariable("username") String username,
            @RequestParam(value = "p", defaultValue = "1") int pageNum,
            Model model) {
        User user = userService.findByUsername(username);
        Page<Reply> replyPage = replyService.findByEditor(user, 20, pageNum);
        model.addAttribute("user",user);
        model.addAttribute("replypage", replyPage);
        model.addAttribute("currentpage",pageNum);
        return "Replies";
    }
}
