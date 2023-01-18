package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepositoryOld;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)   // Spring integration Test를 위해서 사용
@SpringBootTest                // Spring Boot를 띄워서 Test하기 위해서 사용(Spring Container 안에서 사용)
@Transactional                 // Test 케이스에서 Rollback을 해준다(기본이 rollback = true)
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepositoryOld memberRepository;
    @Autowired EntityManager em;



    @Test
    public void 회원가입() throws Exception {
        // given - 머리
        Member member = new Member();
        member.setName("kim");

        // when - 가슴
        Long saveId = memberService.join(member);

        // then - 배
        em.flush();     // --> insert Query는 나가지만, rollback은 이루어진다.
        assertEquals(member, memberRepository.findOne(saveId));

    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");


        // when
        memberService.join(member1);
        memberService.join(member2);       // 예외가 발생해야 한다!!!


        // then
        Assert.fail("예외가 발생해야 한다.");

    }

}