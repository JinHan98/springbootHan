package likelion.springboothan.service;

import likelion.springboothan.domain.Member;

import java.util.List;

public interface MemberService {
    public void save(Member member);
    public Member findById(Long Id);
    public List<Member> findAll();
}
