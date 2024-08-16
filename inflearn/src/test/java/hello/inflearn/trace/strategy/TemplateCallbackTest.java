package hello.inflearn.trace.strategy;

import hello.inflearn.trace.strategy.code.template.Callback;
import hello.inflearn.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     */
    @Test
    void callbackV1(){
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비지니스 로직 1 실행");
            }
        });

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비지니스 로직 2 실행");
            }
        });
    }

    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     */
    @Test
    void callbackV2(){
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("비지니스 로직 1 실행"));
        template.execute(() -> log.info("비지니스 로직 2 실행"));
    }
}
