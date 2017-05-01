package space.shadowc.web;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import space.shadowc.domain.*;
import space.shadowc.service.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private TopicService topicService;

    @Autowired
    private TagService tagService;

    @Value("${resources.path}")
    private String resourcesPath;

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
                           @RequestParam(value = "p", defaultValue = "1") int pageNum,
                           Model model) {
        Post post = postService.findById(id);
        Page<Reply> replyPage = replyService.findByPost(post, 100, pageNum);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (username != "anonymousUser") {
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        model.addAttribute("post", post);
        model.addAttribute("replyPage", replyPage);
        model.addAttribute("currentpage", pageNum);
        return "t";
    }

    @PostMapping("/postreply")
    public String saveReply(String content, int postId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        if (username != "anonymousUser") {
            User user = userService.findByUsername(username);
            Post post = postService.findById(postId);
            Reply reply = new Reply();
            reply.setEditor(user);
            reply.setContent(content);
            System.out.println(content);
            reply.setModifyTime(new Date());
            reply.setPost(post);
            post.setLastReplyTime(new Date());
            replyService.save(reply);
        }
        return "redirect:/t/" + postId;
    }

    @PostMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("FileName") MultipartFile file) {
        DateTime date = DateTime.now();
        String month = date.toString("MM");
        String year = date.toString("yyyy");
        Path location = Paths.get(resourcesPath + year + "/" + month);
        if (!Files.exists(location)) {
            try {
                Files.createDirectories(location);
            } catch (IOException e) {
                return "error|上传失败";
            }
        }

        try {
            if (file.isEmpty()) {
                return "error|上传图片失败";
            }
            Files.copy(file.getInputStream(), location.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            return "error|上传失败";
        }
        return "/resources/" + year + "/" + month + "/" + file.getOriginalFilename();
    }

    @GetMapping("/new")
    public String createPost(Model model) {
        List<Topic> topics = topicService.findAll();
        List<Tag> tags = tagService.findAll();
        model.addAttribute("topics", topics);
        model.addAttribute("tags", tags);
        return "new";
    }

    @PostMapping("/new")
    public String savePost(String title, String content, String topic, String[] tag, RedirectAttributes redirectAttributes) {
        ArrayList<String> errors = new ArrayList<>();
        if (title == null) errors.add("标题不能为空");
        if (title.length() > 120) errors.add("标题超过字符限制");
        if (content.length() > 20000) errors.add("内容超过字符限制");
        if (topicService.findByName(topic) == null) errors.add("不存在的主题");
        if (!tagService.tagValidator(tag)) errors.add("不存在的标签");
        if (errors.size()==0 ) {
            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setTopic(topicService.findByName(topic));
            List<Tag> tags = new ArrayList<>();
            for (String t : tag) {
                tags.add(tagService.findByName(t));
            }
            post.setTags(tags);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            post.setEditor(userService.findByUsername(username));
            post.setCreateTime(new Date());
            post.setVoteCount(0);
            post.setPageView(0);
            post.setLastReplyTime(new Date());
            postService.save(post);
            return "redirect:/t/"+postService.findIdByTitle(title);
        }
        redirectAttributes.addFlashAttribute("errors", errors);
        return "redirect:/new";
    }

    @GetMapping("/initdata")
    public String initdata() {
        for (int k = 0; k < 2; k++) {
            List<Topic> topics = new ArrayList<>();
            List<User> users = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                topics.add(initData.initTopic());
                users.add(initData.initUser());
            }
            for (int i = 0; i < 2; i++) {
                Post post = initData.initPost(users.get(i), topics.get(i));
                for (User user : users) {
                    for (int j = 0; j < 200; j++) {
                        Reply reply = initData.initReply(user, post);
                    }
                }
            }
        }
        return "Index";
    }


}
