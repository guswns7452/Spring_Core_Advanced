package hello.inflearn.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final을 기준으로 자동으로 생성자 주입됨, 대박
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepository;
    
    public void orderItem(String itemID){
        orderRepository.save(itemID);
    }
}
