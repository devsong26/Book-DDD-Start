package com.example.demo.order.ui;

import com.example.demo.order.command.application.OrderProduct;
import com.example.demo.order.command.application.OrderRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class OrderRequestValidator4Spring implements Validator {
    @Override
    public boolean supports(Class<?> aClass){
        return OrderRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors){
        OrderRequest orderReq = (OrderRequest) o;

        String required = "required";
        if(orderReq.getOrderProducts() == null || orderReq.getOrderProducts().isEmpty()){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderProducts", required);
        } else {
            for(int i=0; i< orderReq.getOrderProducts().size(); i++){
                OrderProduct orderProduct = orderReq.getOrderProducts().get(i);
                if(orderProduct.getProductId() == null || orderProduct.getProductId().trim().isEmpty()){
                    errors.rejectValue("orderProducts[" + i + "].productId", required);
                }
                if(orderProduct.getQuantity() <= 0){
                    errors.rejectValue("orderProducts[" + i + "].quantity", "negative");
                }
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.receiver.name", required);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.receiver.phone", required);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.zipCode", required);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.address1", required);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingInfo.address.address2", required);
    }
}
