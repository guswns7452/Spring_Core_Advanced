package hello.inflearn.app.v1;

import hello.inflearn.trace.HelloTrace.HelloTraceV1;
import hello.inflearn.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final을 기준으로 자동으로 생성자 주입됨, 대박
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;

    public void orderItem(String itemID){
        TraceStatus status = null;
        try{
            status = trace.begin(("OrderServiceV1.Request()"));
            orderRepository.save(itemID);
            trace.end(status);

        } catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 함.
        }
    }
}
