package com.example.demo.order.command.domain;

import com.example.demo.member.command.domain.MemberId;

public interface OrdererService {
    Orderer createOrderer(MemberId ordererMemberId);
}
