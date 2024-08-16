package hello.inflearn.app.v5;

import hello.inflearn.trace.LogTrace.LogTrace;
import hello.inflearn.trace.callback.TraceTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemID){
        template.execute("OrderServiceV5.Request()", () -> {
            orderRepository.save(itemID);
            return null;
        });
    }
}
