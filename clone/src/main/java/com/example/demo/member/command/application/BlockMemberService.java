package com.example.demo.member.command.application;

import com.example.demo.member.command.domain.Member;
import com.example.demo.member.command.domain.MemberId;
import com.example.demo.member.command.domain.MemberRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockMemberService {

    private MemberRepository memberRepository;

    public BlockMemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void block(String memberId){
        Member member = memberRepository.findById(new MemberId(memberId))
                .orElseThrow(NoMemberException::new);

        member.block();
    }

}
