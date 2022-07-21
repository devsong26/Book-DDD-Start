package com.example.demo.order.command.application;

import com.example.demo.common.ValidationError;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderRequestValidator {

    private static final String RQ = "required";

    public List<ValidationError> validate(OrderRequest orderRequest){
        List<ValidationError> errors = new ArrayList<>();

        if(orderRequest == null){
            errors.add(ValidationError.of("required"));
        }else{
            if(orderRequest.getOrdererMemberId() == null)
                errors.add(ValidationError.of("ordererMemberId", RQ));
            if(orderRequest.getOrderProducts() == null)
                errors.add(ValidationError.of("orderProducts", RQ));
            if(orderRequest.getOrderProducts().isEmpty())
                errors.add(ValidationError.of("orderProducts", RQ));

            if(orderRequest.getShippingInfo() == null){
                errors.add(ValidationError.of("shippingInfo", RQ));
            } else {
                if(orderRequest.getShippingInfo().getReceiver() == null){
                    errors.add(ValidationError.of("shippingInfo.receiver", RQ));
                } else {
                    if(!StringUtils.hasText(orderRequest.getShippingInfo().getReceiver().getName())){
                        errors.add(ValidationError.of("shippingInfo.receiver.name", RQ));
                    }
                    if(!StringUtils.hasText(orderRequest.getShippingInfo().getReceiver().getPhone())){
                        errors.add(ValidationError.of("shippingInfo.receiver.phone", RQ));
                    }
                    if(orderRequest.getShippingInfo().getAddress() == null){
                        errors.add(ValidationError.of("shippingINfo.address", RQ));
                    } else {
                        if(!StringUtils.hasText(orderRequest.getShippingInfo().getAddress().getZipCode())){
                            errors.add(ValidationError.of("shippingInfo.address.zipCode", RQ));
                        }
                        if(!StringUtils.hasText(orderRequest.getShippingInfo().getAddress().getAddress1())){
                            errors.add(ValidationError.of("shippingInfo.address.address1", RQ));
                        }
                        if(!StringUtils.hasText(orderRequest.getShippingInfo().getAddress().getAddress2())){
                            errors.add(ValidationError.of("shippingInfo.address.address2", RQ));
                        }
                    }
                }
            }
        }
        return errors;
    }
}
