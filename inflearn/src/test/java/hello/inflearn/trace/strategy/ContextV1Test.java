package hello.inflearn.trace.strategy;

import hello.inflearn.trace.strategy.code.strategy.ContextV1;
import hello.inflearn.trace.strategy.code.strategy.Strategy;
import hello.inflearn.trace.strategy.code.strategy.StrategyLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    void ContextV1(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직 1 실행");
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultime = endTime - startTime;
        log.info(Long.toString(resultime));
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직 2 실행");
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultime = endTime - startTime;
        log.info(Long.toString(resultime));
    }

    @Test
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();
    }

    @Test
    void strategyV2(){
        Strategy strategyLogic1 = new Strategy(){
            @Override
            public void call(){
                log.info("비즈니스 로직 1 실행");
            }
        };
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        log.info("{}", strategyLogic1.getClass());
        contextV1.execute();

        Strategy strategyLogic2 = new Strategy(){
            @Override
            public void call(){
                log.info("비즈니스 로직 1 실행");
            }
        };
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        log.info("{}", strategyLogic2.getClass());
        contextV2.execute();
    }

    // 인라인 고치기 : Ctrl + Alt + N
    @Test
    void strategyV3(){
        ContextV1 contextV1 = new ContextV1(new Strategy(){
            @Override
            public void call(){
                log.info("비즈니스 로직 1 실행");
            }
        });
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(new Strategy(){
            @Override
            public void call(){
                log.info("비즈니스 로직 1 실행");
            }
        });
        contextV2.execute();
    }

    // 람다로 고치기 Alt + Enter
    // 람다로 변경하려면 인터페이스에 메소드가 하나만 있어야함
    @Test
    void strategyV4(){
        ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직 1 실행"));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직 1 실행"));
        contextV2.execute();
    }
}
