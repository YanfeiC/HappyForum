package space.shadowc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import space.shadowc.domain.Authority;
import space.shadowc.domain.User;
import space.shadowc.service.AuthorityService;
import space.shadowc.service.AvatarGenerator;
import space.shadowc.service.UserService;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by cyf on 17-3-26.
 */
@Controller
@RequestMapping("/signup")
public class SignUp {

    @Autowired
    private UserService userService;
    @Autowired
    private AvatarGenerator avatarGenerator;
    @Autowired
    private AuthorityService authorityService;

    @GetMapping()
    public String showSignup() {
        return "Signup";
    }

    @PostMapping()
    public String addUser(String username, String passwd, String email) {
        if (StringUtils.isEmpty(username) || !Pattern.matches("^[A-Za-z0-9]+$", username)) {
            System.out.println(1);
            return "Signup";
        } else if (StringUtils.isEmpty(passwd)) {
            System.out.println(2);
            return "Signup";
        } else if (StringUtils.isEmpty(email) || !Pattern.matches("\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?", email)) {
            System.out.println(3);
            return "Signup";
        } else if (userService.findByEmail(email) != null || userService.findByUsername(username) != null) {
            System.out.println(4);
            return "Signup";
        } else {
            User user = new User();
            Date now = new Date();
            user.setUsername(username);
            user.setPassword(new StandardPasswordEncoder("happy").encode(passwd));
            user.setEnabled(true);
            user.setEmail(email);
            user.setJoinTime(now);
            user.setAvatar(avatarGenerator.getAvatar());
            user.setCredit(0);
            userService.save(user);
            Authority authority = new Authority();
            authority.setUsername(username);
            authority.setAuthority("ROLE_USER");
            authorityService.save(authority);
            return "Index";
        }
    }


    @PostMapping("/username")
    public
    @ResponseBody
    boolean checkUsername(String username) {
        return userService.usernameExist(username) ? true : false;
    }

    @PostMapping("/email")
    public
    @ResponseBody
    boolean checkEmail(String email) {
        return userService.emailExist(email) ? true : false;
    }

}
