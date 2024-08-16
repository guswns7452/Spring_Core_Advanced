package hello.inflearn.trace.strategy.code.template;

import hello.inflearn.trace.strategy.code.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback){
        long startTime = System.currentTimeMillis();
        callback.call(); // 위임 (이 부분이 변화함)
        long endTime = System.currentTimeMillis();
        long resultime = endTime - startTime;
        log.info(Long.toString(resultime));
    }
}
