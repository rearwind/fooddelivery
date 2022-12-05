package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class OrderCancelled extends AbstractEvent {

    private Long id;
<<<<<<< HEAD:customer/src/main/java/fooddelivery/domain/OrderPlaced.java
    private Long foodId;
=======
>>>>>>> origin/template:notification/src/main/java/fooddelivery/domain/OrderCancelled.java
    private Integer qty;
    private Long price;
    private String customerId;
    private String address;
    private String status;
    private Long foodId;
}


