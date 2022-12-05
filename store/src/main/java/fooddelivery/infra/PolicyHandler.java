package fooddelivery.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;
import javax.transaction.Transactional;

import fooddelivery.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import fooddelivery.domain.*;


@Service
@Transactional
public class PolicyHandler{
    @Autowired CookingRepository cookingRepository;
    @Autowired InventoryRepository inventoryRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderPlaced'")
    public void wheneverOrderPlaced_CopyOrderInfo(@Payload OrderPlaced orderPlaced){

        OrderPlaced event = orderPlaced;
        System.out.println("\n\n##### listener CopyOrderInfo : " + orderPlaced + "\n\n");

        
        

        // Sample Logic //
        Cooking.copyOrderInfo(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderCancelled'")
    public void wheneverOrderCancelled_UpdateStatus(@Payload OrderCancelled orderCancelled){

        OrderCancelled event = orderCancelled;
        System.out.println("\n\n##### listener UpdateStatus : " + orderCancelled + "\n\n");


        

        // Sample Logic //
        Cooking.updateStatus(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PayAccepted'")
    public void wheneverPayAccepted_UpdateStatus(@Payload PayAccepted payAccepted){

        PayAccepted event = payAccepted;
        System.out.println("\n\n##### listener UpdateStatus : " + payAccepted + "\n\n");


        

        // Sample Logic //
        Cooking.updateStatus(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PayCancelled'")
    public void wheneverPayCancelled_UpdateStatus(@Payload PayCancelled payCancelled){

        PayCancelled event = payCancelled;
        System.out.println("\n\n##### listener UpdateStatus : " + payCancelled + "\n\n");


        

        // Sample Logic //
        Cooking.updateStatus(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderPlaced'")
    public void wheneverOrderPlaced_Decrease(@Payload OrderPlaced orderPlaced){

        OrderPlaced event = orderPlaced;
        System.out.println("\n\n##### listener Decrease : " + orderPlaced + "\n\n");


        

        // Sample Logic //
        Inventory.decrease(event);
        

        

    }

}


