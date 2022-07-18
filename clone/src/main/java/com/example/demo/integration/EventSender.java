package com.example.demo.integration;

import com.example.demo.eventstore.api.EventEntry;

public interface EventSender {
    void send(EventEntry event);
}
