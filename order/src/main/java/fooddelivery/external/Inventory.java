package fooddelivery.external;

import lombok.Data;
import java.util.Date;
@Data
public class Inventory {

    private Long id;
    private String foodId;
    private Integer stock;
}


