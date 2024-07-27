package hello.inflearn.app.v4;

import hello.inflearn.trace.LogTrace.LogTrace;
import hello.inflearn.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;
    
    // 단일 책임의 원칙(SRP)을 만족하는 템플릿 메소드
    
    @GetMapping("/v4/request")
    public String request(String itemId){
        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController.request()");
    }
}
