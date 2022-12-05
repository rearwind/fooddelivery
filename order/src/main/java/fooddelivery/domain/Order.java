package fooddelivery.domain;

import fooddelivery.domain.OrderPlaced;
import fooddelivery.external.Cooking;
import fooddelivery.domain.OrderCancelled;
import fooddelivery.OrderApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Order_table")
@Data

public class Order  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    

    private Long id;
    
    private Long foodId;    
    
    private Integer qty;

    private Long price;

    private String customerId;

    private String address;
 
    private String status;


    // @PrePersist
    // public void checkAvailability(){
    //     if(cookingService().checkAvailability(Long.valueOf(getOrderId())).getStock() < getQty()) throw new RuntimeException("Out of stock");
    // }

    @PrePersist
    public void onPrePersist() {
        
        // fooddelivery.external.Inventory inventory =
        //     OrderApplication.applicationContext.getBean(fooddelivery.external.InventoryService.class)
        //    .getInventory(getFoodId());

        // if(inventory.getStock() < getQty()) throw new RuntimeException("Out of Stock!");

    }

    @PostPersist
    public void onPostPersist(){

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
        
        fooddelivery.external.Payment payment = new fooddelivery.external.Payment();
        payment.setOrderId(getId());
        if (getPrice()!=null)
            payment.setPrice(getPrice());

        OrderApplication.applicationContext.getBean(fooddelivery.external.PaymentService.class)
            .pay(getId(), payment);

        setStatus("주문됨");

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

    }    

    @PreRemove
     public void onPreRemove() {
    //     // Get request from Inventory
    //     fooddelivery.external.Cooking cooking =
    //         OrderApplication.applicationContext.getBean(fooddelivery.external.CookingService.class)
    //        .getCooking(Long.valueOf(getId()));

    //     if ("요리시작됨".equals(cooking.getStatus()) || "요리완료됨".equals(cooking.getStatus())) throw new RuntimeException("요리시작됨!");

         OrderCancelled orderCancelled = new OrderCancelled(this);
         orderCancelled.publishAfterCommit();

    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }



    public void cancelOrder(){

        // if (getStatus()==null || "주문됨".equals(getStatus()) || "주문수락됨".equals(getStatus())) {
        //     this.setStatus("주문취소됨");
        //     OrderCancelled orderCancelled = new OrderCancelled(this);
        //     orderCancelled.publishAfterCommit();
        // }

    }



    public static void updateStatus(OrderAccepted orderAccepted){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process */
        
        repository().findById(orderAccepted.getOrderId()).ifPresent(order->{
            
            order.setStatus("주문수락됨"); // do something
            repository().save(order);


         });
        

        
    }
    public static void updateStatus(OrderRejected orderRejected){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process */
        
        repository().findById(orderRejected.getOrderId()).ifPresent(order->{
            
            order.setStatus("주문거절됨"); // do something
            repository().save(order);


         });
        

        
    }
    public static void updateStatus(CookingStarted cookingStarted){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process */
        
        repository().findById(cookingStarted.getOrderId()).ifPresent(order->{
            
            order.setStatus("요리시작됨"); // do something
            repository().save(order); 


         });

        
    }
    public static void updateStatus(CookingFinished cookingFinished){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process */
        
        repository().findById(cookingFinished.getOrderId()).ifPresent(order->{
            
            order.setStatus("요리완료됨"); // do something
            repository().save(order);


         });


        
    }
    public static void updateStatus(Picked picked){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process */
        
        repository().findById(picked.getOrderId()).ifPresent(order->{
            
            order.setStatus("배달시작됨"); // do something
            repository().save(order);


         });
        

        
    }
    public static void updateStatus(Delivered delivered){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process */
        
        repository().findById(delivered.getOrderId()).ifPresent(order->{ 
            
            order.setStatus("배달완료됨"); // do something
            repository().save(order);


         });
        

        
    }


}
