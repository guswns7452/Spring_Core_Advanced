package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

// 리플렉션을 사용해서 Method로 추상화 했음.
// 공통 로직을 생성할 수 있음

// 리플렉션은 런타임에 동작해서 컴파일에 오류를 잡을 수 없음.
// parameter가 잘못 넘겨져도 런타임 시점에 오류가 나타남
@Slf4j
public class ReflectionTest {
    @Test
    void reflection(){
        Hello target = new Hello();

        // 공통 로직이 동일함
        // 공통 로직 1
        log.info("start");
        String result1 = target.callA(); // 여기만 동적으로 처리하면 좋겠는데?
        log.info("result={}",result1);

        // 공통 로직 2
        log.info("start");
        String result2 = target.callB();
        log.info("result={}",result2);
    }

    @Test
    void reflection1() throws Exception {
        // 클래스 정보 얻기
        // 내부 클래스는 $로 알 수 있음.
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메소드 정보
        Method methodCallA = classHello.getMethod("callA"); // 문자(파라미터)로 값을 변경함 (동적 변경 가능)
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}",result1);

        // callB 메소드 정보
        Method methodCallB = classHello.getMethod("callB"); // 문자(파라미터)로 값을 변경함 (동적 변경 가능)
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}",result2);
    }
    @Test
    void reflection2() throws Exception {
        // 클래스 정보 얻기
        // 내부 클래스는 $로 알 수 있음.
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메소드 정보
        Method methodCallA = classHello.getMethod("callA"); // 문자(파라미터)로 값을 변경함 (동적 변경 가능)
        dynamicCall(methodCallA, target);

        // callB 메소드 정보
        Method methodCallB = classHello.getMethod("callB"); // 문자(파라미터)로 값을 변경함 (동적 변경 가능)
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result1 = method.invoke(target); // 여기만 동적으로 처리하면 좋겠는데?
        log.info("result={}",result1);
    }

    @Slf4j
    static class Hello{
        public String callA(){
            log.info("callA");
            return "A";
        }

        public String callB(){
            log.info("callB");
            return "B";
        }
    }
}
