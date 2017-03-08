package space.shadowc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cyf on 17-3-6.
 */
@Controller
public class Forum {
    @RequestMapping("/happyforum")
    public String showIndex() {
        return "index";
    }
}
