package fooddelivery.domain;

import fooddelivery.domain.PayAccepted;
import fooddelivery.domain.PayCancelled;
import fooddelivery.PaymentApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Payment_table")
@Data

public class Payment  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private Long price;
    
    
    
    
    
    private String status;

    @PostPersist
    public void onPostPersist(){


        PayAccepted payAccepted = new PayAccepted(this);
        payAccepted.publishAfterCommit();



        PayCancelled payCancelled = new PayCancelled(this);
        payCancelled.publishAfterCommit();

    }

    public static PaymentRepository repository(){
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(PaymentRepository.class);
        return paymentRepository;
    }



    public void pay(){
    }

    public static void updateStatus(OrderCancelled orderCancelled){

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        PayCancelled payCancelled = new PayCancelled(payment);
        payCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCancelled.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);

            PayCancelled payCancelled = new PayCancelled(payment);
            payCancelled.publishAfterCommit();

         });
        */

        
    }


}
