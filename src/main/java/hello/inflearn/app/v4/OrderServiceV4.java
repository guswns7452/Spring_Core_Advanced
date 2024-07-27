package hello.inflearn.app.v4;

import hello.inflearn.trace.LogTrace.LogTrace;
import hello.inflearn.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final을 기준으로 자동으로 생성자 주입됨, 대박
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemID){
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemID);
                return null;
            }
        };
        template.execute("OrderServiceV4.Request()");
    }
}
