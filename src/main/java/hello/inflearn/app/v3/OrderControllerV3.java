package hello.inflearn.app.v3;

import hello.inflearn.trace.LogTrace.LogTrace;
import hello.inflearn.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin(("OrderContoller.Request()"));
            orderService.orderItem(itemId); // 핵심 기능
            trace.end(status);
            return "ok";
        } catch (Exception e){
            trace.exception(status, e);
            throw e; 
        }
    }
}
