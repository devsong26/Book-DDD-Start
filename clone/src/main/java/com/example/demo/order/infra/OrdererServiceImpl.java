package com.example.demo.order.infra;

import com.example.demo.member.command.domain.MemberId;
import com.example.demo.member.query.MemberData;
import com.example.demo.member.query.MemberQueryService;
import com.example.demo.order.command.domain.Orderer;
import com.example.demo.order.command.domain.OrdererService;
import org.springframework.stereotype.Service;

@Service
public class OrdererServiceImpl implements OrdererService {
    private final MemberQueryService memberQueryService;

    public OrdererServiceImpl(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    @Override
    public Orderer createOrderer(MemberId ordererMemberId) {
        MemberData memberData = memberQueryService.getMemberData(ordererMemberId.getId());
        return new Orderer(MemberId.of(memberData.getId()), memberData.getName());
    }
}
