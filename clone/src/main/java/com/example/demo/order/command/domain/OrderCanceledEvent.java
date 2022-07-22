package com.example.demo.order.command.domain;

import com.example.demo.common.event.Event;

public class OrderCanceledEvent extends Event {
    private String orderNumber;

    public OrderCanceledEvent(String number) {
        super();
        this.orderNumber = number;
    }

    public String getOrderNumber(){return orderNumber;}
}
