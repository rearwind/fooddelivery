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
    @Autowired OrderRepository orderRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderAccepted'")
    public void wheneverOrderAccepted_UpdateStatus(@Payload OrderAccepted orderAccepted){

        OrderAccepted event = orderAccepted;
        System.out.println("\n\n##### listener UpdateStatus : " + orderAccepted + "\n\n");


        

        // Sample Logic //
        Order.updateStatus(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderRejected'")
    public void wheneverOrderRejected_UpdateStatus(@Payload OrderRejected orderRejected){

        OrderRejected event = orderRejected;
        System.out.println("\n\n##### listener UpdateStatus : " + orderRejected + "\n\n");


        

        // Sample Logic //
        Order.updateStatus(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='CookingStarted'")
    public void wheneverCookingStarted_UpdateStatus(@Payload CookingStarted cookingStarted){

        CookingStarted event = cookingStarted;
        System.out.println("\n\n##### listener UpdateStatus : " + cookingStarted + "\n\n");


        

        // Sample Logic //
        Order.updateStatus(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='CookingFinished'")
    public void wheneverCookingFinished_UpdateStatus(@Payload CookingFinished cookingFinished){

        CookingFinished event = cookingFinished;
        System.out.println("\n\n##### listener UpdateStatus : " + cookingFinished + "\n\n");


        

        // Sample Logic //
        Order.updateStatus(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='Picked'")
    public void wheneverPicked_UpdateStatus(@Payload Picked picked){

        Picked event = picked;
        System.out.println("\n\n##### listener UpdateStatus : " + picked + "\n\n");


        

        // Sample Logic //
        Order.updateStatus(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='Delivered'")
    public void wheneverDelivered_UpdateStatus(@Payload Delivered delivered){

        Delivered event = delivered;
        System.out.println("\n\n##### listener UpdateStatus : " + delivered + "\n\n");


        

        // Sample Logic //
        Order.updateStatus(event);
        

        

    }

}


