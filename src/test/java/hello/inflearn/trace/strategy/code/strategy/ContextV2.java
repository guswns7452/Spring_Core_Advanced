package hello.inflearn.trace.strategy.code.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 전략을 파리미터로 전달하는 방식
 */

@Slf4j
public class ContextV2 {
    public void execute(Strategy strategy){
        long startTime = System.currentTimeMillis();
        strategy.call(); // 위임 (이 부분이 변화함)
        long endTime = System.currentTimeMillis();
        long resultime = endTime - startTime;
        log.info(Long.toString(resultime));
    }
}
