package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BImpl implements Ainterface{
    @Override
    public String Call(){
        log.info("B호출");
        return "B";
    }
}
