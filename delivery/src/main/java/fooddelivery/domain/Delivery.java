package fooddelivery.domain;

import fooddelivery.domain.DeliveryCancelled;
import fooddelivery.domain.Delivered;
import fooddelivery.DeliveryApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Delivery_table")
@Data

public class Delivery  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private String address;
    
    
    
    
    
    private String customerId;
    
    
    
    
    
    private String status;

    @PostPersist
    public void onPostPersist(){


        DeliveryCancelled deliveryCancelled = new DeliveryCancelled(this);
        deliveryCancelled.publishAfterCommit();



        Delivered delivered = new Delivered(this);
        delivered.publishAfterCommit();

    }

    public static DeliveryRepository repository(){
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(DeliveryRepository.class);
        return deliveryRepository;
    }



    public void pick(){
        
        setStatus("배달시작됨");
        Picked picked = new Picked(this);
        picked.publishAfterCommit();

    }

    public void confirmDelivered(){

        setStatus("배달완료됨");
        
    }

    public static void copyOrderInfo(OrderAccepted orderAccepted){

        /** Example 1:  new item */
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderAccepted.getOrderId());
        delivery.setCustomerId(orderAccepted.getCustomerId());
        delivery.setAddress(orderAccepted.getAddress());
        repository().save(delivery);

        

        /** Example 2:  finding and process
        
        repository().findById(orderAccepted.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

        
    }
    public static void updateStatus(CookingStarted cookingStarted){

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process */
        
        repository().findByOrderId(cookingStarted.getOrderId()).ifPresent(delivery->{
            
            delivery.setStatus("요리시작됨"); // do something
            repository().save(delivery);


         });
        

        
    }
    public static void updateStatus(CookingFinished cookingFinished){

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process */
        
        repository().findByOrderId(cookingFinished.getOrderId()).ifPresent(delivery->{
            
            delivery.setStatus("요리완료됨"); // do something
            repository().save(delivery);


         });
        

        
    }


}
