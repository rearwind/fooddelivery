package fooddelivery.external;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {


    /**
     * Fallback
     */
    public Payment getPayment(Long id) {
        Payment payment = new Payment();
        return payment;
    }

    @Override
    public void pay(Long id, Payment payment) {
        // TODO Auto-generated method stub
        
    }
}

