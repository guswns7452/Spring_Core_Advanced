package hello.proxy.proxyFactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {
    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target); // ProxyFactory가 자동으로 프록시 생성해줌
        proxyFactory.addAdvice(new TimeAdvice()); // 우리가 생성한 advice 붙여주기
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy(); // 프록시 객체를 가져올 수 있음
        log.info(String.valueOf(target.getClass()));
        log.info(String.valueOf(proxy.getClass()));

        proxy.save();

        // 프록시가 (자동으로) 잘 생성되었는지 -> 수동으로 생성한건 안됨요
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("구체 클래스만 있으면 CGLIB 사용")
    void concreteProxy(){
        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target); // ProxyFactory가 자동으로 프록시 생성해줌
        proxyFactory.addAdvice(new TimeAdvice()); // 우리가 생성한 advice 붙여주기
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy(); // 프록시 객체를 가져올 수 있음
        log.info(String.valueOf(target.getClass()));
        log.info(String.valueOf(proxy.getClass()));

        proxy.call();

        // 프록시가 (자동으로) 잘 생성되었는지 -> 수동으로 생성한건 안됨요
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLiB를 사용하고, 클래스 기반 프록시 사용")
    void proxyTargetClass(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target); // ProxyFactory가 자동으로 프록시 생성해줌
        proxyFactory.setProxyTargetClass(true); // 항상 CGLIB 기반으로 만들어짐
        proxyFactory.addAdvice(new TimeAdvice()); // 우리가 생성한 advice 붙여주기
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy(); // 프록시 객체를 가져올 수 있음
        log.info(String.valueOf(target.getClass()));
        log.info(String.valueOf(proxy.getClass()));

        proxy.save();

        // 프록시가 (자동으로) 잘 생성되었는지 -> 수동으로 생성한건 안된다.
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }
}


// 최근 스프링 부트는 AOP를 적용할 때 기본적으로 proxyTargetClass = True로 사용함
// 항상 CGLIB를 사용해서 구체 클래스를 기반으로 생성함