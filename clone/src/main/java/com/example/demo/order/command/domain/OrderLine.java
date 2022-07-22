package com.example.demo.order.command.domain;

import com.example.demo.catalog.common.domain.product.ProductId;
import com.example.demo.common.jpa.MoneyConverter;
import com.example.demo.common.model.Money;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class OrderLine {
    @Embedded
    private ProductId productId;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "price")
    private Money price;

    @Column(name = "quantity")
    private int quantity;

    @Convert(converter = MoneyConverter.class)
    @Column(name = "amounts")
    private Money amounts;

    protected OrderLine(){}

    public OrderLine(ProductId productId, Money price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts(){return price.multiply(quantity);}

    public ProductId getProductId(){return productId;}

    public Money getPrice(){return price;}

    public int getQuantity(){return quantity;}

    public Money getAmount() {
        return amounts;
    }
}
