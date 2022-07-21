package com.example.demo.order.command.application;

import com.example.demo.order.NoOrderException;
import com.example.demo.order.command.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancelOrderService {

    private OrderRepository orderRepository;
    private CancelPolicy cancelPolicy;

    public CancelOrderService(OrderRepository orderRepository, CancelPolicy cancelPolicy) {
        this.orderRepository = orderRepository;
        this.cancelPolicy = cancelPolicy;
    }

    @Transactional
    public void cancel(OrderNo orderNo, Canceller canceller){
        Order order = orderRepository.findById(orderNo)
                .orElseThrow(NoOrderException::new);
        if(!cancelPolicy.hasCancellationPermission(order, canceller)){
            throw new NoCancellablePermission();
        }
        order.cancel();
    }

}
