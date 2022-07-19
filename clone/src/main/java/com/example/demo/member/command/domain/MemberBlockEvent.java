package com.example.demo.member.command.domain;

import com.example.demo.common.event.Event;

public class MemberBlockEvent extends Event {
    private String memberId;

    public MemberBlockEvent(String memberId){
        this.memberId = memberId;
    }

    public String getMemberId(){
        return memberId;
    }

}
