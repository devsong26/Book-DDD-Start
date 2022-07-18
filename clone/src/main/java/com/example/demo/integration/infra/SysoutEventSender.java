package com.example.demo.integration.infra;

import com.example.demo.eventstore.api.EventEntry;
import com.example.demo.integration.EventSender;
import org.springframework.stereotype.Component;

@Component
public class SysoutEventSender implements EventSender {
    @Override
    public void send(EventEntry event){
        System.out.println("EventSender send event : " + event);
    }
}
