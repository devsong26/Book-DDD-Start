package com.example.demo.order.command.application;

import com.example.demo.order.NoOrderException;
import com.example.demo.order.command.domain.Order;
import com.example.demo.order.command.domain.OrderNo;
import com.example.demo.order.command.domain.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ChangeShippingService {
    private OrderRepository orderRepository;

    public ChangeShippingService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void changeShipping(ChangeShippingRequest changeReq){
        Optional<Order> orderOpt = orderRepository.findById(new OrderNo(changeReq.getNumber()));
        Order order = orderOpt.orElseThrow(NoOrderException::new);
        order.changeShippingInfo(changeReq.getShippingInfo());
    }
}
