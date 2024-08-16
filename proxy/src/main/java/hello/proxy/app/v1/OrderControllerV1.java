package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 인터페이스에 애노테이션을 사용해도 됨
@RequestMapping // Spring은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨프롤러로 인식
@ResponseBody
public interface OrderControllerV1 {
    @GetMapping("/v1/request")
    String request(@RequestParam ("itemId") String itemId); // interface에서는 꼭 넣어주어야 함. 버전에 따라 작동을 안할 수도 있음.

    @GetMapping("/v1/no-log")
    String noLog();

}
