package hello.inflearn.app.v4;

import hello.inflearn.trace.LogTrace.LogTrace;
import hello.inflearn.trace.template.AbstractTemplate;

public class ControllerToService extends AbstractTemplate {

    String itemId = null;
    private final OrderServiceV4 orderService;
    public ControllerToService(LogTrace trace, String itemId, OrderServiceV4 orderService) {
        super(trace);
        this.itemId = itemId;
        this.orderService = orderService;
    }

    @Override
    protected String call(){
        orderService.orderItem(itemId);
        return "ok";
    }
}
