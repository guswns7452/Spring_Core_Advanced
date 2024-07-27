package hello.inflearn.trace.strategy;

import hello.inflearn.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
    /**
     * 전략 패턴 적용 (의존 관계 주입이 아닌, 파라미터로 전달함)
     */
    @Test
    void StrategyV2(){
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /**
     * 익명 내부 클래스 전략 패턴 적용 (의존 관계 주입이 아닌, 파라미터로 전달함)
     */
    @Test
    void StrategyV3(){
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    /**
     * 람다 전달 전략 패턴 적용 (의존 관계 주입이 아닌, 파라미터로 전달함)
     */
    @Test
    void StrategyV4(){
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직1 실행"));
        context.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
