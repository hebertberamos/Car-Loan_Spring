package com.personalproject.carloan.senders;

import com.personalproject.carloan.entities.RentNote;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RentalDocumentMessageSender {

    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.binding_key_document_generation}")
    private String bindingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void feedDocumentGeneratorQueue(RentNote message){
        rabbitTemplate.convertAndSend(exchange, bindingKey, message);
        System.out.println("INFO |-| message sent to ms.document-generator!");
    }

}
