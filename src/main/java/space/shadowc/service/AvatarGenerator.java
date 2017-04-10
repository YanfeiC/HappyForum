package space.shadowc.service;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by cyf on 17-3-27.
 */
@Component
public class AvatarGenerator {
    public String getAvatar() {
        Random random = new Random();
        int i = random.nextInt(51) + 1;
        return "avatar" + i + ".png";
    }
}
