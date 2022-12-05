package fooddelivery.external;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceFallback implements PaymentService{

    @Override
    public void pay(Long id, Payment payment) {

        System.out.println("결제시스템이 과중된 상태입니다. 잠시 후 다시 결제해 주세요.");
    
    }
}
