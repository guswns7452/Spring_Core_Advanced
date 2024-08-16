package hello.inflearn.app.v5;

import hello.inflearn.trace.LogTrace.LogTrace;
import hello.inflearn.trace.callback.TraceCallback;
import hello.inflearn.trace.callback.TraceTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    // 생성자에서 미리 TraceTemplate 객체를 만들어 두기 때문에, 추후 테스트 시 객체를 생성할 필요가 없음
    // 또한, Singleton으로 생성되어, 한번 만들어두면 재사용 가능함
    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId){
        return template.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        });
    }
}
