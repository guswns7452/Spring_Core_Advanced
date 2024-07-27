package hello.inflearn.trace.template;

import hello.inflearn.trace.LogTrace.LogTrace;
import hello.inflearn.trace.TraceStatus;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {
    public final LogTrace trace;

    public T execute(String message){
        TraceStatus status = null;
        try{
            status = trace.begin(message);
            //로직 호출
            T result = call();
            trace.end(status);
            return result;
        } catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
