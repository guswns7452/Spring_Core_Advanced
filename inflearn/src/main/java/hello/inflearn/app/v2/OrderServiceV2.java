package hello.inflearn.app.v2;

import hello.inflearn.trace.HelloTrace.HelloTraceV2;
import hello.inflearn.trace.TraceId;
import hello.inflearn.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final을 기준으로 자동으로 생성자 주입됨, 대박
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemID){
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId, ("OrderServiceV1.Request()"));
            orderRepository.save(status.getTraceId(), itemID);
            trace.end(status);

        } catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 함.
        }
    }
}
