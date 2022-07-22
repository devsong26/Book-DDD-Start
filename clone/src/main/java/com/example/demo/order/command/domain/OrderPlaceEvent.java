package com.example.demo.order.command.domain;

import java.time.LocalDateTime;
import java.util.List;

public class OrderPlaceEvent {
    private String number;
    private Orderer orderer;
    private List<OrderLine> orderLines;
    private LocalDateTime orderDate;

    public OrderPlaceEvent(String number, Orderer orderer, List<OrderLine> orderLines, LocalDateTime orderDate) {
        this.number = number;
        this.orderer = orderer;
        this.orderLines = orderLines;
        this.orderDate = orderDate;
    }

    public String getNumber() {
        return number;
    }

    public Orderer getOrderer() {
        return orderer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
