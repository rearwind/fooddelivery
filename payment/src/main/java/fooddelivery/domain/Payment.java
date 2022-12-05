package fooddelivery.domain;

import fooddelivery.domain.PayCancelled;
import fooddelivery.domain.PayAccepted;
import fooddelivery.PaymentApplication;
import javax.persistence.*;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;

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

    private String action;


    @PrePersist
    public void onPrePersist(){
        if ("cancel".equals(action)) {
            PayCancelled payCancelled = new PayCancelled();
            BeanUtils.copyProperties(this, payCancelled);
            payCancelled.publish();
        } else {
            PayAccepted payAccepted = new PayAccepted();
            BeanUtils.copyProperties(this, payAccepted);
    
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void beforeCommit(boolean readOnly) {
                    payAccepted.publish();
                }
            });
        }
    }


    @PostPersist
    public void onPostPersist(){


        // PayAccepted payAccepted = new PayAccepted(this);
        // payAccepted.publishAfterCommit();



        // PayCancelled payCancelled = new PayCancelled(this);
        // payCancelled.publishAfterCommit();

    }

 /*    @PrePersist
    public void onPrePersist(){


        PayAccepted payAccepted = new PayAccepted(this);
        payAccepted.publishAfterCommit();

    }
*/

    public static PaymentRepository repository(){
        PaymentRepository paymentRepository = PaymentApplication.applicationContext.getBean(PaymentRepository.class);
        return paymentRepository;
    }



    public void pay(){
        // if ("cancel".equals(action)) {
        //     PayCancelled payCancelled = new PayCancelled();
        //     BeanUtils.copyProperties(this, payCancelled);
        //     payCancelled.publish();
        // }
    }

    public static void updateStatus(OrderCancelled orderCancelled){

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        PayCancelled payCancelled = new PayCancelled(payment);
        payCancelled.publishAfterCommit();
        */

        /** Example 2:  finding and process */
        
        repository().findByOrderId(orderCancelled.getId()).ifPresent(payment->{
            
            payment.setAction("cancel"); // do something
            repository().save(payment);

            PayCancelled payCancelled = new PayCancelled(payment);
            payCancelled.publishAfterCommit();

         });
        

        
    }


}
