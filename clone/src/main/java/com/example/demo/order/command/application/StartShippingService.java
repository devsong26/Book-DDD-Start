package com.example.demo.order.command.application;

import com.example.demo.common.VersionConflictException;
import com.example.demo.order.NoOrderException;
import com.example.demo.order.command.domain.Order;
import com.example.demo.order.command.domain.OrderNo;
import com.example.demo.order.command.domain.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StartShippingService {
    private OrderRepository orderRepository;

    public StartShippingService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void startShipping(StartShippingRequest req){
        Optional<Order> orderOpt = orderRepository.findById(new OrderNo(req.getOrderNumber()));
        Order order = orderOpt.orElseThrow(NoOrderException::new);

        if(order.matchVersion(req.getVersion())) {
            throw new VersionConflictException();
        }

        order.startShipping();
    }
}

