package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;

@Slf4j
public class AdvisorTest {
    /**
     * 프록시 팩토리는 어드바이저가 필수
     * proxyFactory.addAdvice()하면, 자동으로 어드바이저가 지정됨
     * proxyFactory.addAdvisor()해서, 지정할 수도 있음!
     */
    @Test
    void advisorTest1(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice()); // 포인트컷 지정
        proxyFactory.addAdvisor(advisor); // 프록시 팩토리에 어드바이저 지정
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("직접 만든 포인트 컷")
    void advisorTest2(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointCut(), new TimeAdvice()); // 포인트컷 지정
        proxyFactory.addAdvisor(advisor); // 프록시 팩토리에 어드바이저 지정
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }


    @Test
    @DisplayName("스프링이 제공하는 포인트 컷")
    void advisorTest3(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut(); // 이름 기반 포인트컷
        nameMatchMethodPointcut.setMappedName("save");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new TimeAdvice()); // 포인트컷 지정
        proxyFactory.addAdvisor(advisor); // 프록시 팩토리에 어드바이저 지정
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
        
        // JDKRegexMethodPointcut
        // TruePointcut : 항상 참을 반환함
        // AnnotationMatchingPointcut : annotation으로 매칭함
        // AspectJExpressionPointcut -> aspectJ 표현식으로 매칭함
    }
    
    static class MyPointCut implements Pointcut{

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }


    static class MyMethodMatcher implements MethodMatcher {

        private String matchName = "save";
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            boolean result = method.getName().equals(matchName);
            log.info("포인트 컷 호출 method={} targetClass={}", method.getName(), targetClass);
            log.info("포인트 컷 결과 result={}", result);
            return result;
        }

        @Override
        public boolean isRuntime() {
            return false; // 정적으로 매개변수 X. 위 matches
//            return true; // 동적으로 매개변수를 넣을 수 있음. 아래 matches
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
    
}
