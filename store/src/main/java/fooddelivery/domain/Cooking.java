package fooddelivery.domain;

import fooddelivery.StoreApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Cooking_table")
@Data

public class Cooking  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private String foodId;
    
    
    
    
    
    private Integer qty;
    
    
    
    
    
    private String address;
    
    
    
    
    
    private String customerId;
    
    
    
    
    
    private String status;
    
    
    
    
    
    private Boolean available;

    @PostPersist
    public void onPostPersist(){
    }

    public static CookingRepository repository(){
        CookingRepository cookingRepository = StoreApplication.applicationContext.getBean(CookingRepository.class);
        return cookingRepository;
    }



    public void accept(AcceptCommand acceptCommand){

        if (acceptCommand.getAccept()) {
            setStatus("주문수락됨");
            OrderAccepted orderAccepted = new OrderAccepted(this);
            orderAccepted.publishAfterCommit();
        } else {
            setStatus("주문거절됨");
            OrderRejected orderRejected = new OrderRejected(this);
            orderRejected.publishAfterCommit();
        }

    }
    public void start(){
        setStatus("요리시작됨");
        CookingStarted cookingStarted = new CookingStarted(this);
        cookingStarted.publishAfterCommit();

    }
    public void finish(){
        setStatus("요리완료됨");
        CookingFinished cookingFinished = new CookingFinished(this);
        cookingFinished.publishAfterCommit();

    }

    public static void copyOrderInfo(OrderPlaced orderPlaced){

        /** Example 1:  new item */
        Cooking cooking = new Cooking();
        cooking.setOrderId(orderPlaced.getId());
        cooking.setFoodId(orderPlaced.getFoodId());
        cooking.setCustomerId(orderPlaced.getCustomerId());
        cooking.setAddress(orderPlaced.getAddress());
        cooking.setStatus("주문됨");
        repository().save(cooking);


        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);


         });
        */

        
    }
    public static void updateStatus(OrderCancelled orderCancelled){

        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        */

        /** Example 2:  finding and process */
        
        repository().findByOrderId(orderCancelled.getId()).ifPresent(cooking->{
            
            cooking.setStatus("주문취소됨"); // do something
            repository().save(cooking);


         });
        

        
    }
    public static void updateStatus(PayAccepted payAccepted){

        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        */

        /** Example 2:  finding and process */
        
        repository().findByOrderId(payAccepted.getOrderId()).ifPresent(cooking->{
            
            cooking.setStatus("결제승인됨"); // do something
            repository().save(cooking);


         });
        

        
    }
    public static void updateStatus(PayCancelled payCancelled){

        /** Example 1:  new item 
        Cooking cooking = new Cooking();
        repository().save(cooking);

        */

        /** Example 2:  finding and process */
        
        repository().findByOrderId(payCancelled.getOrderId()).ifPresent(cooking->{
            
            cooking.setStatus("결제취소됨"); // do something
            repository().save(cooking);


         });
        

        
    }


}
