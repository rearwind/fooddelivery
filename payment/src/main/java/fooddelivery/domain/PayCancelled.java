package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class PayCancelled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;

    public PayCancelled(Payment aggregate){
        super(aggregate);
    }
    public PayCancelled(){
        super();
    }
}
