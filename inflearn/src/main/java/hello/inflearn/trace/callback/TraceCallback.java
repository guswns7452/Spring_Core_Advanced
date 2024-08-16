package hello.inflearn.trace.callback;

public interface TraceCallback<T> {
    T call();
}
