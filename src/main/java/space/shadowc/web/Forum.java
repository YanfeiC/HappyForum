package space.shadowc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.shadowc.domain.Post;
import space.shadowc.domain.User;
import space.shadowc.service.PostService;
import space.shadowc.service.UserService;

/**
 * Created by cyf on 17-3-6.
 */
@Controller
public class Forum {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

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


}
