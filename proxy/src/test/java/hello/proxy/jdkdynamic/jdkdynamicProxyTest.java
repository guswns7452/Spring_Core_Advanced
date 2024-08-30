package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.Ainterface;
import hello.proxy.jdkdynamic.code.BImpl;
import hello.proxy.jdkdynamic.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class jdkdynamicProxyTest {
    @Test
    void dynamicA(){
        Ainterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        // 인터페이스의 어느 클래스 로더에 할지, 어디 인터페이스 클래스에?, 어떤 로직을 (handler)
//        Object proxy = Proxy.newProxyInstance(Ainterface.class.getClassLoader(), new Class[]{Ainterface.class}, handler);
        Ainterface proxy = (Ainterface) Proxy.newProxyInstance(Ainterface.class.getClassLoader(), new Class[]{Ainterface.class}, handler);

        proxy.Call();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());
    }
    @Test
    void dynamicB(){
        Ainterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        // 인터페이스의 어느 클래스 로더에 할지, 어디 인터페이스 클래스에?, 어떤 로직을 (handler)
//        Object proxy = Proxy.newProxyInstance(Ainterface.class.getClassLoader(), new Class[]{Ainterface.class}, handler);
        Ainterface proxy = (Ainterface) Proxy.newProxyInstance(Ainterface.class.getClassLoader(), new Class[]{Ainterface.class}, handler);

        proxy.Call();
        log.info("targetClass={}",target.getClass());
        log.info("proxyClass={}",proxy.getClass());
    }
}
