package testjpa.member.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import testjpa.member.domain.Gender;
import testjpa.member.domain.Info;
import testjpa.member.domain.Member;
import testjpa.member.repository.InfoRepository;
import testjpa.member.repository.MemberRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	@Autowired InfoRepository infoRepository;
	@Autowired EntityManager em;

	@Test
	@Rollback(false)
	public void 회원가입() throws Exception {
		Member member = new Member();
		member.setName("kim");
		member.setAge(20);
		member.setEmail("kim@naver.com");
		member.setGender(Gender.FEMALE);
		
		Long saveId = memberService.join(member);
		
		Member findMember = memberRepository.findById(saveId).get(); 
		assertEquals(member.getName(), findMember.getName());
	}
	
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");

		memberService.join(member1);
		memberService.join(member2);

		fail("예외가 발생해야 한다.");
	}
	
	@Test
	@Rollback(false)
	public void 병력저장() throws Exception {
		Info info = new Info();
		info.setHistory("병력있음");
		em.persist(info);
		
		Member member = new Member();
		member.setName("yoon");
		member.setAge(20);
		member.setEmail("yoon@naver.com");
		member.setGender(Gender.FEMALE);
		member.setInfo(info);
		em.persist(member);
	}
	
	@Test
	@Rollback(false)
	public void 정보조회() throws Exception {
		Info info = new Info("관절염");
		infoRepository.save(info);
		
		Member member = new Member("userX", 50, "xxx@naver.com", Gender.FEMALE);
		member.setInfo(info);
		memberRepository.save(member);

		Member findMember = memberRepository.findById(member.getId()).get();
		System.out.println("findMember = " + findMember.getName());
		System.out.println("memberHistory = " + findMember.getInfo().getHistory());
	}
	
}
