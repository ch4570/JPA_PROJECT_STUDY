package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /*
    *       Constructor Injection
    * */
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /*
    *       Field Injection
    * */
//    @Autowired
//    private MemberRepository memberRepository;

    /*
    *      Setter Injection
    * */
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /*
    *       회원 가입
    * */
    @Transactional(readOnly = false)
    public Long join(Member member) {
        // 중복 회원 검증
        validateDuplicateMember(member);

        // 회원 한명 저장
        memberRepository.save(member);
        return member.getId();
    }

    /*
    *       회원 전체 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
    *       회원 한명 조회
    * */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

}
