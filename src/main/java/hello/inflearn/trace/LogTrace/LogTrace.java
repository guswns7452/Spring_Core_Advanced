package hello.inflearn.trace.LogTrace;

import hello.inflearn.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin (String message);

    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
