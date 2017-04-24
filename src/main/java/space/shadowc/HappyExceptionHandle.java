package space.shadowc;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

/**
 * Created by cyf on 17-4-21.
 */
@ControllerAdvice
public class HappyExceptionHandle {
    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public String handleError1(MultipartException e){
        return "error|上传失败";
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public String handleError2(MaxUploadSizeExceededException e){
        return "error|上传失败y";
    }
}
