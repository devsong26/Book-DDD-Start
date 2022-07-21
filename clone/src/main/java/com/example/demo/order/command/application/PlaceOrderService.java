package com.example.demo.order.command.application;

import com.example.demo.catalog.common.domain.product.Product;
import com.example.demo.catalog.common.domain.product.ProductId;
import com.example.demo.catalog.common.domain.product.ProductRepository;
import com.example.demo.common.ValidationError;
import com.example.demo.common.ValidationErrorException;
import com.example.demo.order.command.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceOrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrdererService ordererService;

    public PlaceOrderService(ProductRepository productRepository, OrderRepository orderRepository, OrdererService ordererService) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.ordererService = ordererService;
    }

    @Transactional
    public OrderNo placeOrder(OrderRequest orderRequest){
        List<ValidationError> errors = validateOrderRequest(orderRequest);
        if(!errors.isEmpty()) throw new ValidationErrorException(errors);

        List<OrderLine> orderLines = new ArrayList<>();
        for(OrderProduct op : orderRequest.getOrderProducts()){
            Optional<Product> productOpt = productRepository.findById(new ProductId(op.getProductId()));
            Product product = productOpt.orElseThrow(() -> new NoOrderProductException(op.getProductId()));
            orderLines.add(new OrderLine(product.getId(), product.getPrice(), op.getQuantity()));
        }
        OrderNo orderNo = orderRepository.nextOrderNo();
        Orderer orderer = ordererService.createOrderer(orderRequest.getOrdererMemberId());

        Order order = new Order(orderNo, orderer, orderLines, orderRequest.getShippingInfo(), OrderState.PAYMENT_WAITING);
        orderRepository.save(order);
        return orderNo;
    }

    private List<ValidationError> validateOrderRequest(OrderRequest orderRequest) {
        return new OrderRequestValidator().validate(orderRequest);
    }

}
