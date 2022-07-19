package com.example.demo.member.query;

import com.example.demo.member.command.application.NoMemberException;
import org.springframework.stereotype.Service;

@Service
public class MemberQueryService {
    private MemberDataDao memberDataDao;

    public MemberQueryService(MemberDataDao memberDataDao){
        this.memberDataDao = memberDataDao;
    }

    public MemberData getMemberData(String memberId){
        MemberData memberData = memberDataDao.findById(memberId);
        if(memberData == null) throw new NoMemberException();

        return memberData;
    }

}
