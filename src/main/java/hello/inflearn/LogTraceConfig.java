package hello.inflearn;

import hello.inflearn.trace.LogTrace.LogTrace;
import hello.inflearn.trace.LogTrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {


    // 만들어진 interface에 구현체를 Bean으로 등록하는 방법
    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace(); // 싱글톤으로 인스턴스가 빈에 등록됨
    }
}
