package likelion.springboothan.service;

import likelion.springboothan.domain.Member;
import likelion.springboothan.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    @Override
    @Transactional
    public void save(Member member){
        memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public Member findById(Long id){
        Optional<Member> optionalMember =memberRepository.findById(id);
        if(optionalMember.isPresent()){
            Member member=optionalMember.get();
            return member;
        }
        throw new IllegalStateException("not member here");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> findAll(){
        return memberRepository.findAll();
    }

}
