package fooddelivery.domain;

import fooddelivery.StoreApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Inventory_table")
@Data

public class Inventory  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long foodId;
    
    
    
    
    
    private Integer stock;
    
    


    @PostPersist
    public void onPostPersist(){
    }

    public static InventoryRepository repository(){
        InventoryRepository inventoryRepository = StoreApplication.applicationContext.getBean(InventoryRepository.class);
        return inventoryRepository;
    }




    public static void decrease(OrderPlaced orderPlaced){

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        */

        /** Example 2:  finding and process */
        
        repository().findByFoodId(orderPlaced.getFoodId()).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() - orderPlaced.getQty()); // do something
            repository().save(inventory);


         });
        

        
    }
    public static void increase(OrderCancelled orderCancelled){

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        */

        /** Example 2:  finding and process */
        
        repository().findByFoodId(orderCancelled.getFoodId()).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() + orderCancelled.getQty()); // do something
            repository().save(inventory);


         });
        

        
    }


}
