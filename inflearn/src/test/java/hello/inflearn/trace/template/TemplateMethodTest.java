package hello.inflearn.trace.template;

import hello.inflearn.trace.template.code.AbstractTemplate;
import hello.inflearn.trace.template.code.SubClassLogic1;
import hello.inflearn.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    // 템플릿 메소드 패턴을 적용해서 비즈니스 로직을 분리

    @Test
    void templateMethodVO(){
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
    void templateMethodV1(){
        AbstractTemplate template = new SubClassLogic1();
        template.excute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.excute();
    }

    @Test
    void templateMethodV2(){
        // 익명 내부 클래스 사용
        // 장점 : 클래스를 생성하지 않아도 됨
        AbstractTemplate template = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 1 실행");            
            }
        };
        log.info("클래스 이름 = {}",template.getClass());
        template.excute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        template2.excute();
    }
}
