package hello.inflearn.trace;

import lombok.Getter;

@Getter
public class TraceStatus {
    private TraceId traceId;
    private Long startTimeMs;
    private String Message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        Message = message;
    }
}
