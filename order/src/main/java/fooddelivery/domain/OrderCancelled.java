package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import lombok.Data;
import java.util.*;

@Data
public class OrderCancelled extends AbstractEvent {

    private Long id;
<<<<<<< HEAD
    private Long foodId;
=======
>>>>>>> origin/template
    private Integer qty;
    private Long price;
    private String customerId;
    private String address;
    private String status;
    private Long foodId;
}
