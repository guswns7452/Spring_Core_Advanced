package hello.inflearn.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void excute(){
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        call();
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultime = endTime - startTime;
        log.info(Long.toString(resultime));
    }
    protected abstract void call();
}
