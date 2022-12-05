package fooddelivery.external;

import lombok.Data;
import java.util.Date;
@Data
public class Cooking {

    private Long id;
    private Long orderId;
    private Long foodId;
    private Integer qty;
    private String address;
    private String customerId;
    private String status;
}


