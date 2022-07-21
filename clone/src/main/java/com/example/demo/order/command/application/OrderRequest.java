package com.example.demo.order.command.application;

import com.example.demo.member.command.domain.MemberId;
import com.example.demo.order.command.domain.ShippingInfo;

import java.util.List;

public class OrderRequest {
    private List<OrderProduct> orderProducts;
    private MemberId ordererMemberId;
    private ShippingInfo shippingInfo;

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public void setOrdererMemberId(MemberId ordererMemberId) {
        this.ordererMemberId = ordererMemberId;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public MemberId getOrdererMemberId() {
        return ordererMemberId;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }
}
