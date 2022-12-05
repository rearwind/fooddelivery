package fooddelivery.external;

import lombok.Data;
import java.util.Date;
@Data
public class Inventory {

    private Long id;
    private Long foodId;
    private Integer stock;
}


