# 스프링 핵심 원리 - 고급편
<br>

## 섹션 1. 로그 추적기 예제 만들기
![image](https://github.com/guswns7452/Spring_Core_Advanced/assets/31847789/288726e3-6106-4ecd-bdd4-15e462d6a47c)

```
Dictionary
│  InflearnApplication.java
│
├─app
│  ├─v0
│  │      OrderControllerV0.java
│  │      OrderRepositoryV0.java
│  │      OrderServiceV0.java
│  │
│  ├─v1
│  │      OrderControllerV1.java
│  │      OrderRepositoryV1.java
│  │      OrderServiceV1.java
│  │
│  └─v2
│          OrderControllerV2.java
│          OrderRepositoryV2.java
│          OrderServiceV2.java
│
└─trace
    │  TraceId.java
    │  TraceStatus.java
    │
    └─HelloTrace
            HelloTraceV1.java
            HelloTraceV2.java
```

- Class와 Function을 활용한 로그 추적기
- 예외 출력 및 호출된 Depth 구현
- Hash함수를 이용한 고유 ID 생성
<br>

**문제점**
- 호출되는 하위 메소드 (Controller ➜ Service ➜ Repository) 비즈니스 로직에 로그 출력 메소드를 삽입해야함
  - 비즈니스 로직에 삽입되는 만큼, 코드 가독성이 좋지 않음. 
- 처음 호출되는 메소드는 `begin`이지만, 두번째 호출되는 메소드에서는 `beginSync`로 `ID`가 변경되지 않아야함.
  
